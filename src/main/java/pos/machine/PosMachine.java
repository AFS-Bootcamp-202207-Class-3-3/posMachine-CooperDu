package pos.machine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static pos.machine.ItemDataLoader.loadAllItemInfos;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        List<ItemDetail> itemList =  countItems(barcodes);
        String receipt = getReceiptInfomation(itemList);
        return receipt;
    }

    private String getReceiptInfomation(List<ItemDetail> itemList) {
        List<ItemInfo> allItemInfo = getAllItemInfo();
        int total = 0;
        String itemsLineReceipt = "";
        itemList.sort((x, y) -> Double.compare(Double.parseDouble(x.getBarcode().substring(4)), Double.parseDouble(y.getBarcode().substring(4))));
        for (ItemDetail itemDetail : itemList) {
            for (ItemInfo itemInfo : allItemInfo) {
                if (itemDetail.getBarcode().equals(itemInfo.getBarcode())){
                    ItemDetail eachItemReceiptInfo = getEachItemReceiptInfo(itemDetail, itemInfo);
                    itemsLineReceipt = itemsLineReceipt + eachItemReceiptInfo.getItemReceipt();
                    total += eachItemReceiptInfo.getAmount();
                }
            }
        }
        String receipt = String.format("***<store earning no money>Receipt***\n%s----------------------\nTotal: %d (yuan)\n**********************",
                itemsLineReceipt,total);
        return receipt;
    }

    private ItemDetail getEachItemReceiptInfo(ItemDetail itemDetail, ItemInfo itemInfo) {
        int itemAmount = itemDetail.getNum() * itemInfo.getPrice();
        String itemReceipt = String.format("Name: %s, Quantity: %d, Unit price: %d (yuan), Subtotal: %d (yuan)\n", itemInfo.getName(),itemDetail.getNum(),
                itemInfo.getPrice(),itemAmount);
        itemDetail.setAmount(itemAmount);
        itemDetail.setItemReceipt(itemReceipt);
        return itemDetail;
    }

    private List<ItemDetail> countItems(List<String> barcodes) {
        HashMap<String, Integer> itemMap = new HashMap<>();
        ArrayList<ItemDetail> itemDetails = new ArrayList<>();

        for (String barcode : barcodes) {
            if (itemMap.containsKey(barcode)){
                int num = itemMap.get(barcode);
                itemMap.put(barcode,num+1);
            }else {
                itemMap.put(barcode,1);
            }
        }

        for (String key : itemMap.keySet()) {
            itemDetails.add(new ItemDetail(key,itemMap.get(key)));
        }
        return itemDetails;
    }



    public List<ItemInfo> getAllItemInfo(){
        return loadAllItemInfos();
    }


}
