package ru.dfsystems.spring.tutorial.tools;

import org.jooq.SortField;
import org.jooq.TableField;
import org.jooq.TableRecord;

import java.util.*;


public class SQLer {

    public static SortField[] getSortField(Map<String, TableField<? extends TableRecord,?>> orderFields, TableField def, String orderBy, String orderDir) {
        boolean asc = orderDir != null && orderDir.equalsIgnoreCase("asc");
        if (orderBy == null) {
            return new SortField[]{SQLer.sortByAsc(def, asc)};
        }

        List<String> orderArray = new ArrayList<>(Arrays.asList(orderBy.split(",")));
        List<SortField> listSortBy = new ArrayList<>();
        for (String key : orderArray) {
            if(orderFields.containsKey(key)){
                listSortBy.add(SQLer.sortByAsc(orderFields.get(key), asc));
            }
        }

        return listSortBy.toArray(new SortField[0]);
    }


    private static SortField sortByAsc(TableField tableField, boolean isAsc){
        return isAsc ? tableField.asc() : tableField.desc();
    }


}
