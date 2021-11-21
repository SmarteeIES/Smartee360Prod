package za.smartee.support.task;

import com.moko.ble.lib.task.OrderTask;
import za.smartee.support.entity.OrderCHAR;

public class GetAdvSlotDataTask extends OrderTask {

    public byte[] data;

    public GetAdvSlotDataTask() {
        super(OrderCHAR.CHAR_ADV_SLOT_DATA, OrderTask.RESPONSE_TYPE_READ);
    }

    @Override
    public byte[] assemble() {
        return data;
    }
}
