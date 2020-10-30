package org.example.utils;

import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.common.time.Time;
import org.apache.flink.runtime.state.filesystem.FsStateBackend;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class FlinkEnv {

    public static StreamExecutionEnvironment initEnv(Long interval) {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setStreamTimeCharacteristic(TimeCharacteristic.ProcessingTime);

        // 仅仅一次语义
        env.getCheckpointConfig().setCheckpointingMode(CheckpointingMode.AT_LEAST_ONCE);
        // 重启策略
        env.setRestartStrategy(RestartStrategies.fixedDelayRestart(Integer.MAX_VALUE, Time.seconds(60)));
//        env.disableOperatorChaining();

        if (true) {
            env.setStateBackend(new FsStateBackend("file:///tmp/flink/checkpoints", false));
            env.enableCheckpointing(Time.seconds(interval).toMilliseconds());
            return env;
        }

        /*
         * checkpoint设置
         */
        // 容忍cp失败次数
        env.getCheckpointConfig().setTolerableCheckpointFailureNumber(1);

        env.enableCheckpointing(Time.seconds(interval).toMilliseconds(), CheckpointingMode.AT_LEAST_ONCE);
        // 同一时间只允许一个checkpoint进行
        env.getCheckpointConfig().setMaxConcurrentCheckpoints(1);
        // 指定checkpoint执行的超时时间
        env.getCheckpointConfig().setCheckpointTimeout(Time.minutes(3).toMilliseconds());
        // checkpoint完成之后最小等多久可以出发另一个checkpoint
        env.getCheckpointConfig().setMinPauseBetweenCheckpoints(Time.seconds(interval).toMilliseconds());
        // cancel后保留checkpoint
        env.getCheckpointConfig().enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);

        return env;
    }
}
