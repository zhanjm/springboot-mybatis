package com.lazy.mybatis.entity.amap;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 矩形
 */
@Data
@AllArgsConstructor
public class Rectangle {
    private Point maxPoint;//最大坐标点
    private Point minPoint;//最小坐标点

    @Override
    public String toString() {
        return maxPoint +"|" +minPoint;
    }
}
