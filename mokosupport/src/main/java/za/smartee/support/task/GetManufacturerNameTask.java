package za.smartee.support.task;

import com.moko.ble.lib.task.OrderTask;
import za.smartee.support.entity.OrderCHAR;

public class GetManufacturerNameTask extends OrderTask {

    public byte[] data;

    public GetManufacturerNameTask() {
        super(OrderCHAR.CHAR_MANUFACTURER_NAME, OrderTask.RESPONSE_TYPE_READ);
    }

    @Override
    public byte[] assemble() {
        return data;
    }
}
