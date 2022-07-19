package pos.machine;

public class ItemDetail {
    private String barcode;
    private int num;
    private int amount;
    private String itemReceipt;

    public ItemDetail(String barcode, int num) {
        this.barcode = barcode;
        this.num = num;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getItemReceipt() {
        return itemReceipt;
    }

    public void setItemReceipt(String itemReceipt) {
        this.itemReceipt = itemReceipt;
    }
}
