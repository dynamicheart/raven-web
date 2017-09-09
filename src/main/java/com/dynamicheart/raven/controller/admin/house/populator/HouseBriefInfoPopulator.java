package com.dynamicheart.raven.controller.admin.house.populator;

import com.dynamicheart.raven.controller.admin.house.field.HouseBriefInfo;
import com.dynamicheart.raven.controller.common.AbstractDataPopulator;
import com.dynamicheart.raven.model.house.House;
import com.dynamicheart.raven.utils.exception.ConversionException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HouseBriefInfoPopulator extends AbstractDataPopulator<House, HouseBriefInfo> {
    @Override
    protected HouseBriefInfo createTarget() {
        return new HouseBriefInfo();
    }

    @Override
    public HouseBriefInfo populate(House house, HouseBriefInfo houseBriefInfo) throws ConversionException {
        houseBriefInfo.setGroupId(house.getId());
        houseBriefInfo.setGroupName(house.getName());
        return houseBriefInfo;
    }

    public List<HouseBriefInfo> populateList(List<House> houseList) throws ConversionException{
        List<HouseBriefInfo> houseBriefInfoList=new ArrayList<>();
        for(House house:houseList)
            houseBriefInfoList.add(populate(house));
        return houseBriefInfoList;
    }
}
