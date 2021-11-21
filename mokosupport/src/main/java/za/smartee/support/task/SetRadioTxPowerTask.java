package za.smartee.support.task;

import com.moko.ble.lib.task.OrderTask;
import za.smartee.support.entity.OrderCHAR;

public class SetRadioTxPowerTask extends OrderTask {

    public byte[] data;

    public SetRadioTxPowerTask() {
        super(OrderCHAR.CHAR_RADIO_TX_POWER, OrderTask.RESPONSE_TYPE_WRITE);
    }

    @Override
    public byte[] assemble() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
