package za.smartee.support.task;

import com.moko.ble.lib.task.OrderTask;
import za.smartee.support.entity.OrderCHAR;

public class GetUnlockTask extends OrderTask {

    public byte[] data;

    public GetUnlockTask() {
        super(OrderCHAR.CHAR_UNLOCK, OrderTask.RESPONSE_TYPE_READ);
    }

    @Override
    public byte[] assemble() {
        return data;
    }
}
