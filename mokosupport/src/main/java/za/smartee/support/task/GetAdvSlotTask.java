package za.smartee.support.task;

import com.moko.ble.lib.task.OrderTask;
import za.smartee.support.entity.OrderCHAR;

public class GetAdvSlotTask extends OrderTask {

    public byte[] data;

    public GetAdvSlotTask() {
        super(OrderCHAR.CHAR_ADV_SLOT, OrderTask.RESPONSE_TYPE_READ);
    }

    @Override
    public byte[] assemble() {
        return data;
    }
}
