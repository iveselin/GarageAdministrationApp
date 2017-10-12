package com.example.cobeosijek.garageadministrationapp.utilities;

import com.example.cobeosijek.garageadministrationapp.inventory.ExpendableItem;
import com.example.cobeosijek.garageadministrationapp.inventory.ExpendableTypeEnum;
import com.example.cobeosijek.garageadministrationapp.inventory.ReusableItem;
import com.example.cobeosijek.garageadministrationapp.inventory.ReusableTypeEnum;
import com.example.cobeosijek.garageadministrationapp.staff.Apprentice;
import com.example.cobeosijek.garageadministrationapp.staff.FieldOfWorkEnum;
import com.example.cobeosijek.garageadministrationapp.staff.Technician;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cobeosijek on 11/10/2017.
 */

public class GarageCreationUtil {
    private GarageCreationUtil() {
    }

    public static List<Apprentice> createApprentices() {

        List<Apprentice> apprentices = new ArrayList<>();

        apprentices.add(new Apprentice("Ivan", FieldOfWorkEnum.BODYWORKER));
        apprentices.add(new Apprentice("Tomislav", FieldOfWorkEnum.BODYWORKER));
        apprentices.add(new Apprentice("John", FieldOfWorkEnum.MECHANIC));

        return apprentices;
    }

    public static List<Technician> createTehnicians() {

        List<Technician> technicians = new ArrayList<>();

        technicians.add(new Technician("Joseph", FieldOfWorkEnum.MECHANIC, 1));
        technicians.add(new Technician("Mladen", FieldOfWorkEnum.BODYWORKER, 2));

        return technicians;
    }

    public static List<ReusableItem> createReusableItems() {

        List<ReusableItem> reusableItems = new ArrayList<>();

        reusableItems.add(new ReusableItem("Wrench 12", 12, ReusableTypeEnum.WRENCH));
        reusableItems.add(new ReusableItem("Wrench 17", 17, ReusableTypeEnum.WRENCH));
        reusableItems.add(new ReusableItem("Hammer 0.5", 0.5, ReusableTypeEnum.HAMMER));
        reusableItems.add(new ReusableItem("Hammer 1.5", 1.5, ReusableTypeEnum.HAMMER));
        reusableItems.add(new ReusableItem("Spraygun", 1, ReusableTypeEnum.SPRAYGUN));
        reusableItems.add(new ReusableItem("Sander", 1, ReusableTypeEnum.SANDER));

        return reusableItems;
    }

    public static List<ExpendableItem> createExpendableItems() {

        List<ExpendableItem> expendableItems = new ArrayList<>();

        expendableItems.add(new ExpendableItem("Blue paint", ExpendableTypeEnum.SPRAYPAINT));
        expendableItems.add(new ExpendableItem("Sandpaper 200", ExpendableTypeEnum.SANDPAPER));

        return expendableItems;
    }
}
