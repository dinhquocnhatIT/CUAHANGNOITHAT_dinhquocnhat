package dinhquocnhat.service;

import dinhquocnhat.model.Interiors;
import dinhquocnhat.utils.CSVUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class InteriorsService implements IInteriorsService {

    public static String path = "C:\\CUA_HANG_NOI_THAT\\InteriorsMarket\\data\\interiors.csv";

    private static InteriorsService instance;

    public static InteriorsService getInstance() {
        if (instance == null)
            instance = new InteriorsService();
        return instance;
    }


    @Override
    public List<Interiors> getInteriors() {
        List<Interiors> newInteriors = new ArrayList<>();
        List<String> records = CSVUtils.read(path);
        for (String record : records) {
            newInteriors.add(Interiors.parseTechs(record));
        }
        return newInteriors;
    }

    @Override
    public void add(Interiors newInteriors) {
        newInteriors.setCreatDate(Instant.now());
        List<Interiors> interiors = getInteriors();
        interiors.add(newInteriors);
        CSVUtils.write(path, interiors);
    }

    @Override
    public void updateName(Interiors newInteriors) {
        List<Interiors> interiors = getInteriors();
        for (Interiors interiors1 : interiors) {
            if (interiors1.getId() == newInteriors.getId()) {
                String name = interiors1.getNameInteriors();
                if (name != null && !name.isEmpty())
                    interiors1.setNameInteriors(newInteriors.getNameInteriors());
                interiors1.setUpdateDate(Instant.now());
                CSVUtils.write(path, interiors);
                break;
            }
        }
    }

    @Override
    public void updatePrice(Interiors newInteriors) {
        List<Interiors> interiors = getInteriors();
        for (Interiors interiors1 : interiors) {
            if (interiors1.getId() == newInteriors.getId()) {
                double price = interiors1.getPriceInteriors();
                if (price > 0)
                    interiors1.setPriceInteriors(newInteriors.getPriceInteriors());
                interiors1.setUpdateDate(Instant.now());
                CSVUtils.write(path, interiors);
                break;
            }
        }
    }

    @Override
    public void updateQuantity(Interiors newInteriors) {
        List<Interiors> interiors = getInteriors();
        for (Interiors interiors1 : interiors) {
            if (interiors1.getId() == newInteriors.getId()) {
                double Quantity = interiors1.getQuantityInteriors();
                if (Quantity > 0)
                    interiors1.setQuantityInteriors(newInteriors.getQuantityInteriors());
                interiors1.setUpdateDate(Instant.now());
                CSVUtils.write(path, interiors);
                break;
            }
        }
    }

    @Override
    public void updateDescription(Interiors newInteriors) {
        List<Interiors> interiors = getInteriors();
        for (Interiors interiors1 : interiors) {
            if (interiors1.getId() == newInteriors.getId()) {
                String Description = interiors1.getType();
                if (Description != null && !Description.isEmpty())
                    interiors1.setType(newInteriors.getType());
                interiors1.setUpdateDate(Instant.now());
                CSVUtils.write(path, interiors);
                break;
            }
        }
    }


    @Override
    public void remove(Interiors newInteriors) {
        List<Interiors> interiors = getInteriors();
        interiors.removeIf(interior -> interior.getId() == newInteriors.getId());
        CSVUtils.write(path, interiors);
    }


    @Override
    public boolean existById(long id) {
        return getInteriorsById(id) != null;
    }


    @Override
    public Interiors getInteriorsById(long id) {
        List<Interiors> interiors = getInteriors();
        for (Interiors interiors1 : interiors) {
            if (interiors1.getId() == id)
                return interiors1;
        }
        return null;
    }

    @Override
    public void updateNewQuantity(long id, int quantity) {
        List<Interiors> interiors = getInteriors();
        for (Interiors interiors1 : interiors) {
            if (interiors1.getId() == id)
                if (interiors1.getQuantityInteriors() >= quantity) {
                    interiors1.setQuantityInteriors(interiors1.getQuantityInteriors() - quantity);
                    CSVUtils.write(path, interiors);
                    break;
                }
        }
    }

    @Override
    public boolean isExistByName(String name) {
        List<Interiors> interiors = getInteriors();
        for (Interiors interiors1: interiors){
            if (interiors1.getNameInteriors().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }

    public void update() {
        List<Interiors> interiors = getInteriors();
        CSVUtils.write(path, interiors);
    }

    public boolean isExistId(ArrayList<Interiors> list, long id) {
        for (Interiors interiors : list) {
            if (interiors.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
