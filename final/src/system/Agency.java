package system;
import Graphics.MainFrame;
import Vehicles.*;

import javax.swing.*;
import java.util.Random;

public class Agency {

	private IVehicle[] vehicleArr;
	private final String name;
	private int size;
	private final MainFrame frame;

	public int getTotalAgencyTravel() {
		return totalAgencyTravel;
	}

	public void addToTotalAgencyTravel(int totalAgencyTravel) {
		this.totalAgencyTravel += totalAgencyTravel;
	}

	private int totalAgencyTravel;


	public Object getResetLock() {
		return resetLock;
	}
	private final Object resetLock = new Object();

	public String getName(){
		return name;
	}


	public Agency(String name, MainFrame frame) {
		vehicleArr = new IVehicle[size];
		this.name = name;
		this.frame = frame;
	}

	/**
	 * Changing all sea vehicles flags
	 * @param flagName the flag you want to change to
	 */
	public void changeAllSeaFlags(String flagName)  {
		new Thread(() -> {
			synchronized (resetLock){
				delayTime("Changing Flag Update");
				/* Changing all sea vehicles flag */
				for (int i = 0; i < size; i++) {
					if (vehicleArr[i].getSuperClassName().equals("SeaVehicle")) {
						((SeaVehicle)((StatusDecorator)((ColorDecorator)vehicleArr[i]).getVehicle()).getVehicle()).setFlag(flagName);

					}
				}
				frame.successOptionPane("All sea vehicles flag updated successfully","");
			}
		}).start();
	}
	/**

	 Sets the movement of the given vehicle by invoking its movement method with the provided kilometers.
	 @param km the distance traveled in kilometers
	 @param v the vehicle to set the movement for
	 */
	public void setMovement(int km, IVehicle v){
		v.movement(km);
	}

	/**
	 * Reset all vehicles travel distance
	 */
	public void resetTravel()  {
		new Thread(() -> {
			synchronized (resetLock){
				delayTime("Reset Travel Update");
				for (int i = 0; i < getSize(); i++) {
					getVehicles()[i].setTravel(0);
				}
				frame.successOptionPane(" KM reset successfully", "");
				frame.scrollerPanel();
				System.out.println("done");
			}
		}).start();
	}

	/**
	 * Adding vehicle to the agency
	 * @param v the added vehicle
	 * @param msg the message for the optionPane
	 * @param type the type for the optionPane
	 */
	public void addVehicle(IVehicle v, String msg, String type) {
		frame.getFinishButton().setEnabled(false);
		new Thread(() -> {
			synchronized (v){
				delayTime(v.getVehicleType() + " Creating Update");
				size += 1;
				IVehicle[] newVehicleArr = new IVehicle[size];
				System.out.println("B adding new " + newVehicleArr.length);
				System.out.println("B adding orig " + vehicleArr.length);
				System.arraycopy(vehicleArr, 0, newVehicleArr, 0, size - 1);
				newVehicleArr[size - 1] = v;
				System.out.println("A adding new " + newVehicleArr.length);
				System.out.println("A adding orig " + vehicleArr.length);
				vehicleArr = newVehicleArr.clone();
				frame.successOptionPane(msg,type);
				if(frame.getDoneWithBuildThreads() == 0){
					frame.getFinishButton().setEnabled(true);
				}

			}
		}).start();

	}


	/**
	 * Deleting vehicle from the agency
	 * @param v the vehicle to delete
	 * @param vButton the vehicle button to delete
	 */
	public void deleteVehicle(IVehicle v, JButton vButton) throws InterruptedException {
		new Thread(() -> {
			synchronized (v) {
				((StatusDecorator)((ColorDecorator) v).getVehicle()).setStatus("Purchasing");
				delayTime("Purchasing Update");
				((StatusDecorator)((ColorDecorator) v).getVehicle()).setStatus("Available");
				int index = findVehicle(v);
				if (index != -1) {
					size -= 1;
					IVehicle[] newVehicleArr = new IVehicle[size];
//					for(int i=0, k=0;i<newVehicleArr.length;i++){
//						if(vehicleArr[i].equals(v)){
//							newVehicleArr[k]=vehicleArr[i];
//							k++;
//						}
//					}
					if (index >= 0) System.arraycopy(vehicleArr, 0, newVehicleArr, 0, index);
					if (size - index >= 0) {
						System.arraycopy(vehicleArr, index + 1, newVehicleArr, index, size - index);
					}
					vehicleArr = newVehicleArr.clone();
				}
			}
			if(frame.getGallery() != null){
				frame.getGallery().deleteButtons(vButton);
				frame.successOptionPane("purchased", v.getVehicleType());
				frame.scrollerPanel();
			}
			if (frame.getReport() != null){
				frame.getReport().allImagesPanelScroller(this);
			}
		}).start();
	}


	/**

	 Returns an array of all vehicles in the agency.
	 @return the array of vehicles
	 */
	public IVehicle[] getVehicles() {
		/* Get all vehicles array */
		return vehicleArr;
	}
	/**

	 Returns the current size of the agency, indicating the number of vehicles it contains.
	 @return the size of the agency
	 */
	public int getSize() {
		/* Get size */
		return size;
	}
	/**

	 Finds the index of the specified vehicle in the agency.
	 @param v the vehicle to find
	 @return the index of the vehicle in the agency, or -1 if not found
	 */
	public int findVehicle(IVehicle v) {
		/* Find vehicle in the agency */
		if (v == null)
			return -1;
		for (int i = 0; i < size; i++) {
			if (((v).equals(vehicleArr[i]))) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Give a random sleep time for all threads
	 * @param title the title for the processing dialog
	 */
	public void delayTime(String title){
		JDialog dialog = frame.updatingDialog(title);
		try{
			dialog.setVisible(true);
			frame.increaseThreadCount();
			frame.increaseBuildThreadCount();
			Thread.sleep((new Random().nextInt(6) + 3) * 1000);
			dialog.dispose();
			frame.decreaseThreadCount();
			frame.decreaseBuildThreadCount();

		}catch (InterruptedException e){
			System.out.println(e);
		}
	}

	/**

	 Saves the state of the agency and returns a memento object representing the saved state.
	 @return the memento object representing the saved state
	 */
	public Memento saveStateToMemento(){
		return new Memento(vehicleArr.clone(),totalAgencyTravel);
	}
	/**

	 Restores the state of the agency from a memento object.
	 @param memento the memento object representing the saved state
	 */
	public void getStateFromMemento(Memento memento){
		int j = 0;
		size = memento.getVehicles().length;
		vehicleArr = new IVehicle[memento.getVehicles().length];
		for (int i = 0; i < memento.getVehicles().length; i++) {
			vehicleArr[i] = memento.getVehicles()[i];
			vehicleArr[i].setTravel(memento.getVehiclesKM()[i]);
			if (vehicleArr[i].getSuperClassName().equals("SeaVehicle")){
				((SeaVehicle)((StatusDecorator)((ColorDecorator)vehicleArr[i]).getVehicle()).getVehicle()).setFlag(memento.getVehiclesFlags().get(j));
				j++;
			}
		}
		totalAgencyTravel = memento.getAgencyTotalKM();
	}

}



