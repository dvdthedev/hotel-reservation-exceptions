package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {

    private int roomNumber;
    private Date checkin;
    private Date checkout;

    public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getCheckin() {
        return checkin;
    }


    public Date getCheckout() {
        return checkout;
    }


    public Reservation(int roomNumber, Date checkin, Date checkout) {
        this.roomNumber = roomNumber;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public long duration(){
        long diff = checkout.getTime() - checkin.getTime();
         return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public String updateDates(Date checkIn, Date checkOut){

        Date now = new Date();

        if (checkIn.before(now) || checkOut.before(now)){
             return "Error in reservation: Reservation dates for update must be future dates";
        }
        if (!checkOut.after(checkIn)){
            return "Error in reservation, Check-out date must be after check-in date";
        }


        this.checkin = checkIn;
        this.checkout = checkOut;
        return null;
    }



    @Override
    public String toString(){
        return "Room " +
                roomNumber +
                ", check-in: " +
                sdf.format(checkin) +
                ", check-out: " +
                sdf.format(checkout) +
                ", " + duration() +
                " nights";
    }
}
