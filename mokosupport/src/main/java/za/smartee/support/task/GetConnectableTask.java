package za.smartee.support.task;

import com.moko.ble.lib.task.OrderTask;
import za.smartee.support.entity.OrderCHAR;

public class GetConnectableTask extends OrderTask {

    public byte[] data;

    public GetConnectableTask() {
        super(OrderCHAR.CHAR_CONNECTABLE, OrderTask.RESPONSE_TYPE_READ);
    }

    @Override
    public byte[] assemble() {
        return data;
    }
}
