package com.atux.service.qryMapper;

import com.atux.bean.precios.Local;
import com.atux.bean.precios.LocalFlt;

import java.util.List;

/**
 * Created by JAVA on 15/11/2014.
 */
public interface LocalQryMapper {

    public List<Local> findList(LocalFlt localFlt);
}
