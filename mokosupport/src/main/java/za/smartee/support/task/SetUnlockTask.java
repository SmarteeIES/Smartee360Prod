package za.smartee.support.task;

import com.moko.ble.lib.task.OrderTask;
import za.smartee.support.entity.OrderCHAR;

public class SetUnlockTask extends OrderTask {

    public byte[] data;

    public SetUnlockTask() {
        super(OrderCHAR.CHAR_UNLOCK, OrderTask.RESPONSE_TYPE_WRITE);
    }

    @Override
    public byte[] assemble() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
