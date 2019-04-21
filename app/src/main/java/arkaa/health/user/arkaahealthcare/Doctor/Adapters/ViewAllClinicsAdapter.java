package arkaa.health.user.arkaahealthcare.Doctor.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import arkaa.health.user.arkaahealthcare.Doctor.Activity.DoctorsDetail;
import arkaa.health.user.arkaahealthcare.Doctor.Activity.schedule;
import arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfDoctors.ClinicDetail;
import arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfDoctors.DoctorTiming;
import arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfDoctors.Timing_;
import arkaa.health.user.arkaahealthcare.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ViewAllClinicsAdapter extends RecyclerView.Adapter<ViewAllClinicsAdapter.ViewHolder> {
    private List<ClinicDetail> listOfClinicsArrayList;
    private String doctorUniqueIdIntent;
    private int index;
    private int specializationId;
    private Context context;


    // Provide a suitable constructor (depends on the kind of dataset)
    public ViewAllClinicsAdapter(List<ClinicDetail> listofLabs, String doctorUniqueIdIntent, int index, int specializationId, Context context) {
        this.listOfClinicsArrayList = listofLabs;
        this.doctorUniqueIdIntent = doctorUniqueIdIntent;
        this.index = index;
        this.specializationId = specializationId;
        this.context = context;
    }


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View mTextView;

        public TextView clinicNameTextView;
        public TextView clinicAddress1TextView;
        public TextView clinicAddress2TextView;
        public TextView clinicTimingMondayTextView;
        public TextView consultationFeesTextView;


        public Button callClinicButton;
        public Button button;


        public ViewHolder(View v) {
            super(v);
            mTextView = v;

            clinicNameTextView = v.findViewById(R.id.clinic_name);
            clinicAddress1TextView = v.findViewById(R.id.clinic_address_1);
            clinicAddress2TextView = v.findViewById(R.id.clinic_address_2);
            clinicTimingMondayTextView = v.findViewById(R.id.clinic_timing_monday);
            consultationFeesTextView = v.findViewById(R.id.clinic_consultation_fees);

            callClinicButton = v.findViewById(R.id.call_button);
            button = v.findViewById(R.id.book_now);


        }
    }


    // Create new views (invoked by the layout manager)
    @Override
    public ViewAllClinicsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_all_clinics_recyclerview, parent, false);

        return new ViewAllClinicsAdapter.ViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewAllClinicsAdapter.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
//        holder.mTextView.setText(mDataset[position]);

        if (listOfClinicsArrayList != null) {

            String clinicName = "" + listOfClinicsArrayList.get(position).getClinicName();
            String clinicAddress = "" + listOfClinicsArrayList.get(position).getAddress();
            String city = "" + listOfClinicsArrayList.get(position).getCity();
            String state = "" + listOfClinicsArrayList.get(position).getState();

            String clinicStartTime;
            try {
                clinicStartTime = "" + listOfClinicsArrayList.get(position).getAffiliationDetails().get(0).getDoctorTimings().get(0).getTimings().get(0).getStartTime();
            } catch (Exception e) {
                Log.v("tryCatch", "DoctorDetail:(clinicStartTime)  =" + e.getMessage());
                Log.v("tryCatchExp", "ViewAllClinicsAdapter " + "clinicStartTime Exception e =" + e.getMessage());
                clinicStartTime = "";
            }

            String clinicEndTime;
            try {
                clinicEndTime = listOfClinicsArrayList.get(position).getAffiliationDetails().get(0).getDoctorTimings().get(0).getTimings().get(0).getEndTime();
            } catch (Exception e) {
                Log.v("tryCatch", "DoctorDetail:(clinicEndTime)  =" + e.getMessage());
                Log.v("tryCatchExp", "ViewAllClinicsAdapter" + "clinicEndTime Exception e =" + e.getMessage());

                clinicEndTime = "";
            }

            String fees = "";
            if (listOfClinicsArrayList != null) {

//                  fees = clinicDetailsArray.get(0).getFirstConsultationFee().toString();

                if (listOfClinicsArrayList.get(position).getAffiliationDetails() != null) {
                    fees = "RS " + listOfClinicsArrayList.get(position).getAffiliationDetails().get(0).getFirstConsultationFee();
                } else {
                    //   fees = "RS 650";

                }


                String clinicFirstConsultationFees = listOfClinicsArrayList.get(position).getAffiliationDetails().get(0).getFirstConsultationFee().toString();
                final String clinicPhoneNo = "" + listOfClinicsArrayList.get(position).getMobile();
                final String clinicTelephoneNo = "" + listOfClinicsArrayList.get(position).getTelephoneNo();
                final int officeId = listOfClinicsArrayList.get(position).getAffiliationDetails().get(0).getId();
                final int clinicIndex = position;


                List<DoctorTiming> doctorTimings = listOfClinicsArrayList.get(position).getAffiliationDetails().get(0).getDoctorTimings();


                holder.clinicNameTextView.setText("" + clinicName);
                holder.clinicAddress1TextView.setText("" + clinicAddress);
                holder.clinicAddress2TextView.setText(state + "," + " " + city);
                // holder.clinicTimingMondayTextView.setText(clinicStartTime + " - " + clinicEndTime + ")");
                holder.consultationFeesTextView.setText(fees + " Consultation fees");

                final String startTimeEndTime = displayTimeSlots(doctorTimings, getCurrentDateGlobal(), holder);


                holder.button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //OLD CONFIG
//                        Intent intent = new Intent(view.getContext(),schedule.class);
                        //OLD CONFIG
                        //NEW CHANGES
                        Intent intent = new Intent(view.getContext(), DoctorsDetail.class);

                        //NEW CHANGES
                        intent.putExtra("officeId", officeId);
                        intent.putExtra("doctorUniqueId", doctorUniqueIdIntent);

                        // NEW CHANGES
                        intent.putExtra("INDEX", index);
                        intent.putExtra("SPECIALIZATION_ID", specializationId);
                        intent.putExtra("DOCTOR_UNIQUE_ID", doctorUniqueIdIntent);
                        intent.putExtra("CLINIC_INDEX", clinicIndex);
                        intent.putExtra("START_TIME_END_TIME", startTimeEndTime);


                        //NEW CHANGES

                        view.getContext().startActivity(intent);
                    }
                });

                holder.callClinicButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + clinicTelephoneNo));
                        v.getContext().startActivity(intent);
                    }
                });


            }


        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return listOfClinicsArrayList.size();
    }


    //wrapperfunction
    public String displayTimeSlots(List<DoctorTiming> doctorTimings, String appointmentDate, ViewAllClinicsAdapter.ViewHolder holder) {

        String startTimeEndTime = "";
        if (doctorTimings == null) {
            startTimeEndTime = "Doctor not available Today";
            holder.clinicTimingMondayTextView.setText(startTimeEndTime);
            return startTimeEndTime;
        }

        String dayName = getDayNameGlobal(appointmentDate);
        boolean isAvailable;
        DoctorTiming doctorTime;
        String startTime = "";
        String endTime = "";
        List<String> timeslotArrayList;
        Boolean isCurrentDate = isCurrentDateGlobal(appointmentDate);
        getDayNameGlobal(appointmentDate);
        int dayValue = getDayValue(appointmentDate);
        if (dayValue != 0) {
            doctorTime = doctorTimings.get(getDayPositionGlobal(dayValue));
        } else {
            Log.v("docTimeParcel", "Toast : if (dayValue != 0) else body ");

            //Toast.makeText(context, "Doctor not available on this date", Toast.LENGTH_LONG).show();
            startTimeEndTime = "Doctor not available Today";
            holder.clinicTimingMondayTextView.setText(startTimeEndTime);

            return startTimeEndTime;
        }

        isAvailable = doctorTime.getIsAvailable();
        if (!isAvailable) {
            Log.v("docTimeParcel", "Toast :(!isAvailable) ");

            //  Toast.makeText(context, "Doctor not available Today", Toast.LENGTH_LONG).show();
            startTimeEndTime = "Doctor not available Today";

            holder.clinicTimingMondayTextView.setText(startTimeEndTime);

            return startTimeEndTime;
        } else {
            List<Timing_> timings = doctorTime.getTimings();
            if (timings != null) {

                if (timings.size() != 0) {

                    endTime = "" + getTwelvehourTimeFormat(timings.get(0).getEndTime());
                    startTime = "" + getTwelvehourTimeFormat(timings.get(0).getStartTime());

                    Log.v("timeParcelValue", "end time =" + endTime);
                    Log.v("timeParcelValue", "startTime isCurrentDate =" + startTime);


                }


                if (!startTime.equals("") && !startTime.equals("null") && !endTime.equals("") && !endTime.equals("null")) {
                    startTimeEndTime = dayName + " :" + startTime + " - " + endTime;
                    holder.clinicTimingMondayTextView.setText(startTimeEndTime);
                    return startTimeEndTime;
                } else {
                    Log.v("docTimeParcel", "Toast :if(!startTime.equals(\"\") && !endTime.equals(\"\")) else body ");

                    // Toast.makeText(context, "Doctor not available on this date", Toast.LENGTH_LONG).show();
                    startTimeEndTime = "Doctor not available Today";
                    holder.clinicTimingMondayTextView.setText(startTimeEndTime);

                    return startTimeEndTime;
                }

                //   enableTimeSlotEditText(timeslotArrayList);



            }
        }

        return startTimeEndTime;

    }

    //wrapperfunction


    //utils
    public boolean isCurrentDateGlobal(String date) {
        Calendar rightNow = Calendar.getInstance();
        Date currentTime = rightNow.getTime();
        long currentTimeConst = currentTime.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
        String currentDate = dateFormat.format(currentTime);

        if (date.equals(currentDate)) {
            return true;
        } else {
            return false;
        }


    }

    public String getDayNameGlobal(String date) {

        Date dateObj = null;
        SimpleDateFormat DATEformat = new SimpleDateFormat("MM/dd/yy");

        Date newDate2 = null;
        SimpleDateFormat DAYformat = new SimpleDateFormat("EEEE");

        try {
            dateObj = DATEformat.parse(date);
            Calendar c = Calendar.getInstance();
            c.setTime(dateObj); // yourdate is an object of type Date
            int dayValue = c.get(Calendar.DAY_OF_WEEK);

            String dayName = DAYformat.format(dateObj);
            String dayNameUpperCase = dayName.toUpperCase();

            Log.v("getDayValueNew", "dayValue =" + dayValue);
            Log.v("getDayValueNew", "dayName =" + dayName);

            return dayNameUpperCase;


        } catch (Exception e) {
            Log.v("tryCatch", "getDayValue e = " + e.getMessage());
        }

        return null;

    }

    public int getDayValue(String date) {

        Date dateObj = null;
        SimpleDateFormat DATEformat = new SimpleDateFormat("MM/dd/yy");

        Date newDate2 = null;
        SimpleDateFormat DAYformat = new SimpleDateFormat("EEEE");
        try {
            dateObj = DATEformat.parse(date);
            Calendar c = Calendar.getInstance();
            c.setTime(dateObj); // yourdate is an object of type Date
            int dayValue = c.get(Calendar.DAY_OF_WEEK);

            String dayName = DAYformat.format(dateObj);

            Log.v("getDayValueNew", "dayValue =" + dayValue);
            Log.v("getDayValueNew", "dayName =" + dayName);

            Log.v("docScheduleTimeSlot", "getDayValue() (try) dayValue = " + dayValue);
            return dayValue;

        } catch (Exception e) {
            Log.v("tryCatch", "getDayValue e = " + e.getMessage());
        }

        Log.v("docScheduleTimeSlot", "getDayValue() (Error) dayValue = " + 0);

        return 0;

    }

    public int getDayPositionGlobal(int dayValue) {
        if (dayValue != 1) {
            return dayValue - 2;
        }
        return 6;

    }

    public ArrayList<String> getAllTimingArrayGlobal(boolean isCurrentDate, String date, String startTime, String endTime) {


        try {
            ArrayList<String> timeSlotArrayList = new ArrayList<>();
            Calendar rightNow = Calendar.getInstance();
            Date currentTime = rightNow.getTime();
            long currentTimeConst = currentTime.getTime();

            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
            String changedTime = dateFormat.format(currentTime);


            String date1 = date;//current date
            String date2 = date;//current date

//        String time1 = "09:00 AM";// start time or current time
//        String time2 = "10:00 PM";// end time

            String time1 = startTime;// start time or current time
            String time2 = endTime;// end time


            String format = "MM/dd/yy hh:mm a";
            String timeSlot = "hh:mm a";

            SimpleDateFormat sdf = new SimpleDateFormat(format);
            SimpleDateFormat timeSlotFormat = new SimpleDateFormat(timeSlot);

            Date startTimedateObj1 = null;
            try {
//            dateObj1 = sdf.parse(date1 + " " + time1);
                startTimedateObj1 = sdf.parse(changedTime + " " + time1);
                Log.v("timeSlots", "date obj 1 =" + startTimedateObj1);

            } catch (ParseException e) {
                e.printStackTrace();
                Log.v("timeSlots", "exception 1=" + e.getMessage());

            }


            Date endTimedateObj2 = null;
            try {
//            dateObj2 = sdf.parse(date2 + " " + time2);

                endTimedateObj2 = sdf.parse(changedTime + " " + time2);
                Log.v("timeSlots", "date obj 2 =" + endTimedateObj2);


            } catch (ParseException e) {
                e.printStackTrace();
                Log.v("timeSlots", "exception 2=" + e.getMessage());

            }
            System.out.println("Date Start: " + startTimedateObj1);
            System.out.println("Date End: " + endTimedateObj2);

//Date d = new Date(dateObj1.getTime() + 3600000);


            long dif = startTimedateObj1.getTime();
            while (dif < endTimedateObj2.getTime()) {
                Date slot = new Date(dif);
                Log.v("timeSlots", "time slots = " + slot);


                if(!isCurrentDate){
                    timeSlotArrayList.add(timeSlotFormat.format(slot));
                    Log.v("timeSlots1123", "if(!isCurrentDate) = " + timeSlotFormat.format(slot));
                }
                //    Log.v("timeSlots11", "time slot array list " + timeSlotArrayList.get(timeSlotArrayList.size() - 1));


                if (currentTimeConst < slot.getTime()) {
                    if(isCurrentDate) {
                        timeSlotArrayList.add(timeSlotFormat.format(slot));
                    }
                    Log.v("timeSlots1123", "time slots in proper format(if statement) = " + timeSlotFormat.format(slot));

                } else {
                    if(isCurrentDate) {
                        Log.v("timeSlots1123", "time slots in proper format(else statement) = " + timeSlotFormat.format(slot));

                    }


                }
                dif += (3600000 / 2);

            }

//        Calendar rightNow = Calendar.getInstance();

            Log.v("cURRENTtIME", "time " + System.currentTimeMillis());
            Log.v("cURRENTtIME", "calender time = " + rightNow.getTime());
            Log.v("cURRENTtIME", "calender time(msec) = " + rightNow.getTimeInMillis());
            Log.v("cURRENTtIME", "calender timeZone = " + rightNow.getTimeZone());


            Log.v("cURRENTtIME", "calender changedTime = " + changedTime);


            return timeSlotArrayList;

        } catch (Exception e) {
            Log.v("tryCatch", "getAllTimeslotsGlobal Exception e =" + e.getMessage());
            Log.v("tryCatch", "getAllTimeslotsGlobal Exception e =" + e.getLocalizedMessage());
            Log.v("tryCatch", "getAllTimeslotsGlobal Exception e =" + e.getStackTrace());

            return new ArrayList<String>();

        }


    }

    public String getTwelvehourTimeFormat(String realTime) {

        String str;
        try {

            String time = realTime;

//            String inputPattern = "yyyy-MM-dd'T'HH:mm:ss";
            String inputPattern = "kk:mm";

            String outputPattern = "hh:mm a";

            SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
            SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

            Date date = null;
            str = null;

            try {
                date = inputFormat.parse(time);
                str = outputFormat.format(date);
                Log.v("new_time1", "try(time)date = " + date + "str = " + str);
            } catch (ParseException e) {
                e.printStackTrace();
                Log.v("new_time1", "|(time)exception = " + e.getMessage());
                Log.v("new_time1", "|(time)exception =" + e.getStackTrace());
                Log.v("new_time1", "|(time)exception =" + e.getLocalizedMessage());


            }

        } catch (Exception e) {
            Log.v("new_time1", "|(time)exception main =" + e.getMessage());
            Log.v("new_time1", "|(time)exception main =" + e.getStackTrace());
            Log.v("new_time1", "|(time)exception main =" + e.getLocalizedMessage());


            str = "";
            return str;
        }


        Log.v("new_time1", "(time)str = " + str);
        return str;


    }

    public String getCurrentDateGlobal() {
        Calendar rightNow = Calendar.getInstance();
        Date currentTime = rightNow.getTime();

        long currentTimeConst = currentTime.getTime();


        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
        String currentDate = dateFormat.format(currentTime);

        Log.v("getCurrentDate", "getFormattedDateTime =" + currentDate);

        return currentDate;
    }

    public String getCurrentTimeGlobal() {
        Calendar rightNow = Calendar.getInstance();
        Date currentTime = rightNow.getTime();

        //  long currentTimeConst = currentTime.getTime();


        SimpleDateFormat dateFormat = new SimpleDateFormat("kk:mm");
        String changedTime = dateFormat.format(currentTime);

        Log.v("getFormattedTimeGlobal", "getFormattedDateTime =" + changedTime);

        return changedTime;
    }
    //utils



}


