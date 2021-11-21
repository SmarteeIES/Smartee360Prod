package za.smartee.support.task;

import com.moko.ble.lib.task.OrderTask;
import za.smartee.support.entity.OrderCHAR;

public class GetDeviceTypeTask extends OrderTask {

    public byte[] data;

    public GetDeviceTypeTask() {
        super(OrderCHAR.CHAR_DEVICE_TYPE, OrderTask.RESPONSE_TYPE_READ);
    }

    @Override
    public byte[] assemble() {
        return data;
    }
}
