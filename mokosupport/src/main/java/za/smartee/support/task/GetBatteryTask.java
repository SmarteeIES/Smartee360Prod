package za.smartee.support.task;

import com.moko.ble.lib.task.OrderTask;
import za.smartee.support.entity.OrderCHAR;

public class GetBatteryTask extends OrderTask {

    public byte[] data;

    public GetBatteryTask() {
        super(OrderCHAR.CHAR_BATTERY, OrderTask.RESPONSE_TYPE_READ);
    }

    @Override
    public byte[] assemble() {
        return data;
    }
}
