package com.jdbc.spring.common;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BatchBatchPreparedStatementSetter implements org.springframework.jdbc.core.BatchPreparedStatementSetter {

    private static final int DEFAULT_BATCH_SIZE = 2000;

    private boolean isRowFirstParam = true;

    /**
     * 参数类型由isRowFirstParam决定，为true时代表行参数优化模式。
     * 行参数优化模式：结构类似<参数列表1，参数列表2，... 参数列表n>。<br/>
     * 列参数优化模式：结构类似<参数1列表，参数2列表，... 参数n列表>。当某个参数列表长度为1时，会重复使用。
     */
    private List<List> argListList = null;

    private int totalBatchSize = DEFAULT_BATCH_SIZE;

    private int totalIndex = 0;

    private int batchNo = 0;

    private int batchExeSize = DEFAULT_BATCH_SIZE;

    public BatchBatchPreparedStatementSetter(List<List> argListList, int totalBatchSize, int batchExeSize, boolean isRowFirstParam) {
        this.argListList = argListList;
        this.totalBatchSize = totalBatchSize;
        this.batchExeSize = batchExeSize;
        this.isRowFirstParam = isRowFirstParam;
        if (this.batchExeSize > this.totalBatchSize) {
            this.batchExeSize = this.totalBatchSize;
        }
    }

    public List<List> getArgList() {
        return this.argListList;
    }

    public void setArgList(List<List> argList) {
        this.argListList = argList;
    }

    public int getTotalBatchSize() {
        return this.totalBatchSize;
    }

    public void setTotalBatchSize(int totalBatchSize) {
        this.totalBatchSize = totalBatchSize;
    }

    public void setBatchExeSize(int batchExeSize) {
        this.batchExeSize = batchExeSize;
    }

    public int getBatchExeSize() {
        return this.batchExeSize;
    }

    public boolean isRowFirstParam() {
        return this.isRowFirstParam;
    }

    public void setRowFirstParam(boolean isRowFirstParam) {
        this.isRowFirstParam = isRowFirstParam;
    }

    public int getBatchSize() {
        batchNo++;
        return Math.min(totalBatchSize - totalIndex, batchExeSize);
    }

    public void setValues(PreparedStatement ps, int exeIndex) throws SQLException {
        // (batchNo-1)*batchExeSize+index
        int paramIndex = 1;
        if (isRowFirstParam) {
            for (Object obj : argListList.get(totalIndex)) {
            	ps.setObject(paramIndex++, obj);
            }
        }
        else {
            for (List tmpArgList : argListList) {
                if (tmpArgList.size() == 1) {
                    ps.setObject(paramIndex, tmpArgList.get(0));
                }
                else {
                    ps.setObject(paramIndex, tmpArgList.get(totalIndex));
                }
                paramIndex++;
            }
        }
        totalIndex++;
    }

}