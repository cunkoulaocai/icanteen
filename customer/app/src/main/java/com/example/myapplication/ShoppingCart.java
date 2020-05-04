package com.example.myapplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.example.myapplication.Product;
import com.example.myapplication.ProductCategory;
import com.example.myapplication.ShoppingEntity;
import com.example.myapplication.ShoppingCartChangeEvent;
import com.example.myapplication.EventUtil;

/**
 * ���ﳵ������
 */
public class ShoppingCart {

    private String mBusinessId;
    private Map<String, ShoppingEntity> mShoppingList;

    private static ShoppingCart instance;

    public static ShoppingCart getInstance() {
        if (instance == null) {
            instance = new ShoppingCart();
        }

        return instance;
    }

    private ShoppingCart() {
        mShoppingList = new HashMap<>();
    }

    private void sendChangeEvent() {
        EventUtil.sendEvent(new ShoppingCartChangeEvent());
    }

    /**
     * �����ﳵ�������Ʒ
     * @param product ��ӵ���Ʒ����
     * @return �Ƿ���ӳɹ�
     */
    public boolean push(Product product) {
        String id = product.getId();
        if (mShoppingList.isEmpty()) {
            // ��һ�������Ҫ��¼�̼�ID
            mBusinessId = product.getBusinessId();
            // ͨ��Product�����ʼ��һ��ShoppingEntity����
            ShoppingEntity entity = ShoppingEntity.initWithProduct(product);
            mShoppingList.put(id, entity);
            sendChangeEvent();

            return true;
        } else if (mBusinessId.equals(product.getBusinessId())) {
            ShoppingEntity entity = mShoppingList.containsKey(id) ? mShoppingList.get(id) : null;
            if (entity == null) {
                entity = ShoppingEntity.initWithProduct(product);
            } else {
                entity.setQuantity(entity.getQuantity() + 1);
            }
            mShoppingList.put(id, entity);
            sendChangeEvent();

            return true;
        }

        return false;
    }

    /**
     * �����ﳵ�������Ʒ
     * @param product ��Ҫ���ٵ���Ʒ����
     * @return �Ƿ���ٳɹ�
     */
    public boolean pop(Product product) {
        String id = product.getId();
        if (mShoppingList.containsKey(id)) {
            ShoppingEntity entity = mShoppingList.get(id);
            int originQuantity = entity.getQuantity();
            if (originQuantity > 1) {
                entity.setQuantity(--originQuantity);
                mShoppingList.put(id, entity);
                sendChangeEvent();

                return true;
            } else if (originQuantity == 1) {
                mShoppingList.remove(id);
                sendChangeEvent();

                return true;
            }
        }

        return false;
    }

    /**
     * �����ﳵ�����ָ����������Ʒ
     * @param product ��Ҫ��ӵ���Ʒ����
     * @return �Ƿ���ӳɹ�
     */
    public boolean set(Product product, int quantity) {
        String id = product.getId();
        if (mShoppingList.isEmpty()) {
            // ��һ�������Ҫ��¼�̼�ID
            mBusinessId = product.getBusinessId();
        }

        if (mBusinessId.equals(product.getBusinessId())) {
            ShoppingEntity entity = mShoppingList.containsKey(id) ? mShoppingList.get(id) : null;
            if (entity == null) {
                entity = ShoppingEntity.initWithProduct(product);
            }
            if (quantity > 0) {
                entity.setQuantity(quantity);
                mShoppingList.put(id, entity);
            } else {
                mShoppingList.remove(id);
            }
            sendChangeEvent();

            return true;
        }

        return false;
    }

    /**
     * ����һ��
     * @param shoppingEntities
     */
    public void again(List<ShoppingEntity> shoppingEntities) {
        mShoppingList.clear();
        for (ShoppingEntity entity : shoppingEntities) {
            Product product = entity.getProduct();
            if (product != null) {
                mBusinessId = product.getBusinessId();
                mShoppingList.put(product.getId(), entity);
            }
        }
        sendChangeEvent();
    }

    /**
     * ��չ��ﳵ�����������
     */
    public void clearAll() {
        mShoppingList.clear();
        sendChangeEvent();
    }

    /**
     * ��ȡ�̼�ID
     * @return �̼�ID
     */
    public String getBusinessId() {
        return mBusinessId;
    }

    /**
     * ��ȡ���ﳵ��������Ʒ���ܼ�
     * @return ��Ʒ�ܼ�
     */
    public double getTotalPrice() {
        double totalPrice = 0.0d;
        for (ShoppingEntity entry : mShoppingList.values()) {
            totalPrice += entry.getTotalPrice();
        }

        return totalPrice;
    }

    /**
     * ��ȡ���ﳵ��������Ʒ������
     * @return ��Ʒ����
     */
    public int getTotalQuantity() {
        int totalQuantity = 0;
        for (ShoppingEntity entry : mShoppingList.values()) {
            totalQuantity += entry.getQuantity();
        }

        return totalQuantity;
    }

    /**
     * ��ȡ���ﳵ��ָ����Ʒ���������
     * @param category ָ������Ʒ����
     * @return ��Ʒ����
     */
    public int getQuantityForCategory(ProductCategory category) {
        int totalQuantity = 0;
        for (ShoppingEntity entry : mShoppingList.values()) {
            Product product = entry.getProduct();
            if (product != null && product.getCategoryId().equals(category.getId())) {
                totalQuantity += entry.getQuantity();
            }
        }

        return totalQuantity;
    }

    /**
     * ��ȡ���ﳵ��ָ����Ʒ������
     * @param product ָ������Ʒ
     * @return ��Ʒ����
     */
    public int getQuantityForProduct(Product product) {
        String id = product.getId();
        if (mShoppingList.containsKey(id)) {
            return mShoppingList.get(id).getQuantity();
        }

        return 0;
    }

    /**
     * ��ȡ���ﳵ��ѡ���б�
     * @return ѡ���б�
     */
    public List<ShoppingEntity> getShoppingList() {
        List<ShoppingEntity> entities = new ArrayList<>();
        for (ShoppingEntity entry : mShoppingList.values()) {
            entities.add(entry);
        }

        return entities;
    }
}