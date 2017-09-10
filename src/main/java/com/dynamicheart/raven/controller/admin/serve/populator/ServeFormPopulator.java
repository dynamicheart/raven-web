package com.dynamicheart.raven.controller.admin.serve.populator;

import com.dynamicheart.raven.controller.admin.serve.field.ServeForm;
import com.dynamicheart.raven.controller.common.AbstractDataPopulator;
import com.dynamicheart.raven.model.serve.Serve;
import com.dynamicheart.raven.utils.exception.ConversionException;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class ServeFormPopulator extends AbstractDataPopulator<Serve,ServeForm>{

    @Override
    protected ServeForm createTarget() {
        return new ServeForm();
    }

    @Override
    public ServeForm populate(Serve serve, ServeForm serveForm) throws ConversionException {
        serveForm.setCreatedDate(serve.getCreatedDate());
        serveForm.setStatus(serve.getStatus());
        serveForm.setContent(serve.getContent());
        serveForm.setHouseId(serve.getHouseId());
        serveForm.setId(serve.getId());
        serveForm.setManId(serve.getManId());
        serveForm.setType(serve.getType());

        SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        serveForm.setCreatedDateString(sdf.format(serve.getCreatedDate()));
        return serveForm;
    }

    public List<ServeForm> populateList(List<Serve> serveList) throws ConversionException{
        List<ServeForm> serveFormList=new ArrayList<>();
        for(Serve serve:serveList)
            serveFormList.add(populate(serve,new ServeForm()));
        return serveFormList;
    }
}
