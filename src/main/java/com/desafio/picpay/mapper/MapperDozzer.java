package com.desafio.picpay.mapper;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.awt.print.PageFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class MapperDozzer{
    Mapper mapper= DozerBeanMapperBuilder.buildDefault();

    public <Origin,Destination> Destination mapperObject(Origin object,Class<Destination> classPointer){
        return mapper.map(object,classPointer);
    }

    public <Origin,Destination> Page<Destination> mapperPage(Page<Origin> pageOrigin,Class<Destination> classPointer){
       Page<Destination> pageTypeDestination=pageOrigin.map(object->{
        Destination objectDestination=mapper.map(object,classPointer);
        return objectDestination;
       });
       return pageTypeDestination;
    }

    public <Origin,Destination>List<Destination> mapperList(List<Origin> lisOrigin,Class<Destination> classPointer){
        List<Destination> listDestination=new ArrayList<>();
        lisOrigin.forEach(object->{
            listDestination.add(mapper.map(object,classPointer));
        });
        return listDestination;
    }
}
