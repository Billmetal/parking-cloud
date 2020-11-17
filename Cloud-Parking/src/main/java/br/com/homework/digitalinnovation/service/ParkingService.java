package br.com.homework.digitalinnovation.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.homework.digitalinnovation.exceptions.ParkingNotFoundException;
import br.com.homework.digitalinnovation.model.Parking;

@Service
public class ParkingService {
	
	private static Map<String,Parking> parkingMap = new HashMap<String, Parking>();
	
	static {
		var id = getUUID();
		Parking parking = new Parking(id, "MMS-1111", "SP", "VW Gol", "Preto");
		parkingMap.put(id,parking);
		id = getUUID();
		parking = new Parking(id, "MMS-1111", "SP", "VW Gol", "Preto");
		parkingMap.put(id,parking);
	}
	
	private static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	public List<Parking> findAll(){
		return parkingMap.values().stream().collect(Collectors.toList());
	}
	
	public Parking findById(String id) {
		Parking parking = parkingMap.get(id);
		if(parking == null) {
			throw new ParkingNotFoundException(id);
		}
		return parking;
	}
	
	public Parking create(Parking parkingCreate) {
        String uuid = getUUID();
        parkingCreate.setId(uuid);
        parkingCreate.setEntryDate(LocalDateTime.now());
        parkingMap.put(uuid, parkingCreate);
        return parkingCreate;
    }

    public void delete(String id) {
        findById(id);
        parkingMap.remove(id);
    }

    public Parking update(String id, Parking parkingCreate) {
        Parking parking = findById(id);
        parking.setColor(parkingCreate.getColor());
        parkingMap.replace(id, parking);
        return parking;
    }

    public Parking exit(String id) {
        //recuperar o estacionado
    	Parking parking = findById(id);
        //atualizar data de saida
    	parking.setExitDate(LocalDateTime.now());
        //calcular o valor
    	System.out.println(parking.addBill());
        return parking;
    }
	
}
