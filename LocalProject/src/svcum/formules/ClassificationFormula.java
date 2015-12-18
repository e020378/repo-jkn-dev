/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  org.apache.log4j.Logger
 */
package svcum.formules;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
//import org.apache.log4j.Logger;

public abstract class ClassificationFormula {
//    protected Logger log4j;
    protected Map caracteristicsMap;
    protected Map productNamesMap;

    public ClassificationFormula() {
//        this.log4j = Logger.getLogger(this.getClass());
        this.caracteristicsMap = new HashMap();
        this.productNamesMap = new HashMap();
    }

    public void addCaracteristic(String string, String string2) {
        this.caracteristicsMap.put(string, string2);
    }

    public void addCaracteristics(Map map) {
        this.caracteristicsMap.putAll(map);
    }

    public void clearCaracteristics() {
        this.caracteristicsMap.clear();
    }

    public abstract int evaluate() throws Exception;

    public double getDoubleCaracteristic(String string) throws Exception {
        String string2 = (String)this.caracteristicsMap.get(string.toUpperCase());
        if (string2 != null) {
            return Double.parseDouble(string2.trim());
        }
        return Double.NaN;
    }

    public String getStringCaracteristic(String string) throws Exception {
        String string2 = (String)this.caracteristicsMap.get(string.toUpperCase());
        if (string2 == null) {
            return "";
        }
        return string2.trim();
    }

    public String toString() {
//        Iterator<K> iterator = this.caracteristicsMap.keySet().iterator();
//        StringBuffer stringBuffer = new StringBuffer();
//        while (iterator.hasNext()) {
//            K k = iterator.next();
//            V v = this.caracteristicsMap.get(k);
//            stringBuffer.append(k.toString() + " : " + v + "; ");
//        }
//        return stringBuffer.toString();
    	return this.toString();
    }

    public int getIdSU(String string) throws Exception {
//        this.log4j.debug((Object)("Recherche de l'id du produit : " + string));
        String string2 = (String)this.productNamesMap.get(string);
        if (string2 != null) {
            return Integer.parseInt(string2);
        }
        throw new Exception("L'ID SteelUser du produit : " + string + " est introuvable");
    }

    public Map getProductNamesMap() {
        return this.productNamesMap;
    }

    public void setProductNamesMap(Map map) {
        this.productNamesMap = map;
    }
}