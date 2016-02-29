package com.kafka;

import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

/**
 * @Author Fangys
 * @Desc  ����
 * @Date 2016��2��22�� ����8:57:59
 * @Version 1.x 
 */
public class RoutePartition implements Partitioner{

	public RoutePartition(VerifiableProperties v){
		
	}
	
	@Override
	public int partition(Object obj, int numPartitions) {
		int partition = 0;
        if (obj instanceof String) {
            String key=(String)obj;
            int offset = key.lastIndexOf('.');
            if (offset > 0) {
                partition = Integer.parseInt(key.substring(offset + 1)) % numPartitions;
            }
        }else{
            partition = obj.toString().length() % numPartitions;
        }
         
        return partition;
	}

}
