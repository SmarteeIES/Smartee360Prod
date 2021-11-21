package za.smartee.support.task;

import com.moko.ble.lib.task.OrderTask;
import za.smartee.support.entity.OrderCHAR;

public class SetAdvSlotDataTask extends OrderTask {

    public byte[] data;

    public SetAdvSlotDataTask() {
        super(OrderCHAR.CHAR_ADV_SLOT_DATA, OrderTask.RESPONSE_TYPE_WRITE);
    }

    @Override
    public byte[] assemble() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
