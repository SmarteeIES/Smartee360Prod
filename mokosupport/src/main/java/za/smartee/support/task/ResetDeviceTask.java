package za.smartee.support.task;

import com.moko.ble.lib.task.OrderTask;
import za.smartee.support.entity.OrderCHAR;


public class ResetDeviceTask extends OrderTask {

    public byte[] data = new byte[]{0x0b};

    public ResetDeviceTask() {
        super(OrderCHAR.CHAR_RESET_DEVICE, OrderTask.RESPONSE_TYPE_WRITE);
    }

    @Override
    public byte[] assemble() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
