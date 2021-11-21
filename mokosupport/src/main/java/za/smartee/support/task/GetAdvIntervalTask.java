package za.smartee.support.task;

import com.moko.ble.lib.task.OrderTask;
import za.smartee.support.entity.OrderCHAR;

public class GetAdvIntervalTask extends OrderTask {

    public byte[] data;

    public GetAdvIntervalTask() {
        super(OrderCHAR.CHAR_ADV_INTERVAL, OrderTask.RESPONSE_TYPE_READ);
    }

    @Override
    public byte[] assemble() {
        return data;
    }
}
