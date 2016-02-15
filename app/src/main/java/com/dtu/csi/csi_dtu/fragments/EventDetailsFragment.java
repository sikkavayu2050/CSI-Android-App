package com.dtu.csi.csi_dtu.fragments;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dtu.csi.csi_dtu.R;

import java.util.HashMap;
import butterknife.ButterKnife;

public class EventDetailsFragment extends Fragment {
    static HashMap<String, Event> events = new HashMap<>();
    String eventName;
    TextView description, contact, contact2, location;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.event_description, null);
        setUpView(root);
        try {
            eventName = getArguments().getString("name");
            description = (TextView) root.findViewById(R.id.event_detail);
            contact = (TextView) root.findViewById(R.id.event_contact);
            contact2 = (TextView) root.findViewById(R.id.event_contact2);
            location = (TextView) root.findViewById(R.id.location);
            Event event = events.get(eventName);
            description.setText(event.description);
            if(event.contact.equals("empty"))
                contact.setText("");
            else if(event.contact.length() == 0)
                contact.setText(getString(R.string.contact_1));
            else
                contact.setText(event.contact);
            contact.setPaintFlags(contact.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            contact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String number = contact.getText().toString();
                    int index = number.indexOf('+');
                    number = number.substring(index, index + 13);
                    startActivity(new Intent(Intent.ACTION_DIAL).setData(
                            Uri.parse(
                                    "tel:" + number.trim())));
                }
            });
            if(event.contact2.equals("empty"))
                contact2.setText("");
            else if(event.contact2.length() == 0)
                contact2.setText(getString(R.string.contact_2));
            else
                contact2.setText(event.contact2);
            contact2.setPaintFlags(contact.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            contact2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String number = contact2.getText().toString();
                    int index = number.indexOf('+');
                    number = number.substring(index, index + 13);
                    startActivity(new Intent(Intent.ACTION_DIAL).setData(
                            Uri.parse(
                                    "tel:" + number.trim())));
                }
            });
            if(event.location.length() == 0)
                event.location = "P.R. Desk";
            location.setText(event.location);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return root;
    }
    static void generateEvents() {
        events.put("code golf", new Event("Code Golf", "The lesser the better! A solution to the problem is great, " +
                "but a shorter solution is even better, so " +
                "write less to win more! Write a code in minimum characters for a problem on SPOJ and the less " +
                "you write the more you score.",
                "Shubham : +918130920855",
                "Mayank : +919999475036",
                "19th Feb\tSPOJ\t9:00P.M.-12:00A.M."));
        events.put("cryptex", new Event("Cryptex", "A tad bit of common sense coupled with good observation " +
                "is what all needed for Cryptex, the " +
                "online code breaking event. Kind of treasure hunt, dedicated to testing not only your general " +
                "knowledge, but also lateral thinking skills. Ready to challenge the Sherlock hidden inside " +
                "you", "Anish : +919582796770\n\nAryan :  +919582108912", "Lovepreet : +919560925110", "16-18thFeb\tWeb Application\t12:00A.M."));
        events.put("neuvo gengo", new Event("Neuvo Gengo", "Coding, REDEFINED… No C/ C++/ Java. " +
                "New problem and a new programming language." +
                "Same logic but code it in new syntax ,are you up for this challenge?",
                "Mayank : +919999475036\n\nShubham Garg : +918130920855",
                "Vayu : +919818633396", "Day1\tTW1GF3, TW1GF4 9.00 A.M. - 5.00 P.M"));
        events.put("cranium", new Event("Cranium", "The battle for all the CODERS on the biggest coding battleground " +
                "the CODECHEF/SPOJ. An " +
                "online programming contest which gives you an opportunity to flaunt your coding skills.",
                "Mayank Gupta : +919999475036",
                "Shubham Garg : +918130920855", "Day1\tTW1GF3, TW1GF4\t9.00 A.M. - 5.00 P.M"));
        events.put("znapz", new Event("Znapz", "Have you got the skill to capture a moment in a frame? " +
                "Yes? Then it’s time to use your fancy " +
                "camera and photography skills to win. ZNAPZ is an online photography competition in which a " +
                "theme will be provided on which the contestants have to click pictures and let your pictures do " +
                "the talking.",
                "Vayu : +919818633396",
                "Anish : +919582796770",
                ""));
        events.put("pancratium", new Event("Pancratium", "Is virtual reality your reality? " +
                "Pancratium,the LAN gaming event, with games like FIFA, counter " +
                "strike, NFS etc. Fierce competition and gaming at a new level.",
                "Deepanshu : +919467905846",
                "Ashwani : +917503460216",
                "FIFA - AI Lab\n" +
                "NFS - IPM Lab Lab \n" +
                "Counter Strike - CN Lab\n"+
                "12:00P.M."));
        events.put("troll-it", new Event("Troll - It !", "Trolls, the best way to showcase humor. That natural humor and " +
                "a little bit of artistic skills is all " +
                "that is required to ace at this competition. Get set to exhibit your witty one-liners here!",
                "Vayu : +919818633396",
                "Anish : +919582796770",
                ""));
        events.put("mathrix", new Event("Mathrix", "A math quiz and a brain game for those who love numbers! " +
                "Logical thinking, speed, accuracy and love for mathematics is all that is required to be the " +
                "champion of Mathrix.",
                "Kunal Puri : +919643505439",
                "Anish  : +919582796770\n\nVayu : +919818633396",
                "PR Desk\t12.00 P.M."));
        events.put("sudo code", new Event("Sudo Code", "An exhilarating combination of SUDOKU and CODING. Nine " +
                "output based questions in " +
                "C/C++ , a partially filled Sudoku. Brush up your programming knowledge to get the output and " +
                "tickle the grey matter to complete the puzzle.\n",
                "Shubham Garg : +918130920855",
                "Mayank Gupta : +919999475036",
                "TW1GF3, TW1GF4\t9.00 A.M. - 5.00 P.M"));
        events.put("mind mumble", new Event("Mind Mumble", "A general knowledge quiz for folks who are " +
                "well versed with the world around them. Mind " +
                "mumble holds thrilling twists and turns for those who know it all!",
                "Ekta Punia : +919958347642",
                "Vayu : +919818633396",
                "Day1\tTW1FF1, TW1FF2\t9.00 A.M."));
        events.put("smash dub", new Event("Smash Dub", "An exciting online competition " +
                "for our dubsmash fans and freaks to post their exciting" +
                "videos and win exciting prizes.",
                "Kunal Puri : +919643505439",
                "Anish : +919582796770",
                ""));
        events.put("ideate", new Event("Ideate" , "The software competition and exhibition aims to unravel " +
                "the hidden innovative software " +
                "developers within us. This is an on the spot competition to display UML diagrams for given case study.",
                "Parul Goswami : +918587074621",
                "Shikha Raina : +918527831213",
                "Day 2\tComputation Lab\t9.00 A.M."));
        events.put("cogitate", new Event("Cogitate", "Brainstorm your mind to prepare a software project plan. " +
                "Enumerate and examine factor " +
                "affecting a software project like technologies, risk, cost, resources and personnel for given case study.",
                "Aashray Yadav : +919871022467",
                "Jatin Kumar : +919717380513",
                "Day 2\tComputation Lab\t11.00 A.M."));
        events.put("three lines of code", new Event("Three Lines of Code", "Three lines of code is an " +
                "exigent event that will be held to challenge your " +
                "spontaneity and flare in debugging and problem solving. Problem solving can involve modifying the " +
                "highlighted three lines of the code or arranging the given modules or segments of program under time " +
                "pressure and make the program work.",
                "Gauri Rastogi : +918860581845)",
                "Gautam Bathla : +919716617015",
                "Day2\tIPM Lab\t11.00 A.M.-3:00P.M."));
        events.put("switch programming", new Event("Switch Programming", "Cook your own code and have a taste of what " +
                "your teammate has made for " +
                "you. Provided with a desired output, you and your teammate will code alternatively by switching on " +
                "each intermittent sound of the buzzer.",
                "Gautam Bathla : +919716617015",
                "Mayank Gupta : +919999475036",
                "Day 2\tDBMS Lab\t9.00 A.M."));
        events.put("testing geeks", new Event("Testing Geeks", "Writing code may be sometime easier but " +
                "analyzing it can be tough. This event " +
                "challenge your software testing ability. So do you have acumen of writing test cases for given code? ",
                "Rahul Arora : +919654107522",
                "empty",
                "Day1\tCA Lab\t11.00 A.M."));
        events.put("line seguidor", new Event("Line Seguidor", "The objective of this contest is for a " +
                "robot to follow a black line on a white background, " +
                "without losing the line, and navigating several 90 degree turns. The robot to complete the course in " +
                "the shortest period of time while accurately tracking the course line from start to finish wins.",
                "Sajal : +919716686404",
                "empty",
                "Day 2\tSPS Room (11,12)\t11.00 A.M."));
        events.put("machine learning mania", new Event("Machine Learning Mania",
                "This competition is about creating an artificial intelligence program that " +
                "could read the data sets, analyse it, self - learn it and then produce the optimized results. A workshop " +
                "will be conducted before the competition about the basics of machine learning.",
                "Sanket Kashyap : +918447998647",
                "Vineet Garg : +919654851230",
                "Day1\tECE Seminar Hall\t12.00 P.M."));
        events.put("bug trail", new Event("Bugtrail", "The competitors will be provided with a certain " +
                "programming codes, they have to find the " +
                "bug and debug all the codes within the specified time.",
                "Vivek : +918285647823",
                "Tanvi : +919582912444",
                "Day1\tTW1FF3, TW1FF4\t12.00 P.M."));
        events.put("codewhiz", new Event("Codewiz", "Codewiz is an online competitive coding " +
                "competition of two days that would be " +
                "conducted on CodeChef. The competitors will be provided the problems which they have to solve in a \n" +
                "specified time limit.",
                "Rachit : +918800665675",
                "Hardik : +919971488189",
                "20th Feb\tCodeChef\t9PM - 12AM"));
        events.put("dtu great marathon", new Event("DTU Great Marathon", "Marathon run for DTU students and " +
                "other college students in which the " +
                "participants have to run through the perimeter of DTU campus, marathon is for boys and girls both.",
                "Tezaswy : +919013220642",
                "Snehal : +99718441404",
                ""));
        events.put("shades of mystery", new Event("Shades of Mystery", "Shades of Mystery is a quizzing " +
                "competition containing three types of quiz viz., " +
                "aptitude quiz, sitcom quiz and sports quiz.",
                "Kashish : +917838998985",
                "Ashwani : +917503505790",
                "Day1\tTW1FF2\t9.00 A.M"));
        events.put("codefest", new Event("CodeFest", "The coding sprint for passionate programmers. " +
                "Long running coding competition. Do you " +
                "have the stamina to solve 10 problems in 5 hours of code?",
                "Anish Philip : +919582796770",
                "Mayank Gupta : +919999475036",
                "Day1\tDBMS Lab\t11.00 A.M."));
        events.put("paper presentation", new Event("Paper Presentation", "The research paper presentation " +
                "competition, prefect platform for all the " +
                "researchers to get their work evaluated. If you have done any research work then this is the " +
                "to present up your research paper in front of industrial and professional judge panel.",
                "Rahul Arora : +919654107522",
                "empty",
                ""));
        events.put("conference", new Event("Conference", "Bridging the distance between students and industrial " +
                "professionals, it aims at helping " +
                "the students create a better and thorough perspective regarding the real world and the opportunities " +
                "that it beholds. With talks by industrial experts from various fields, it sure is an enlightening and " +
                "learning experience for everyone who attends.",
                "Aman Sharma : +9999860519",
                "Jatin Kumar : +9717380513",
                "Day3\tSenate Hall\t9:30A.M."));
    }
    void setUpView(ViewGroup root){
        ButterKnife.bind(this, root);
    }
    public static class Event {
        String name, description, contact, contact2, location;
        public Event(String name, String description, String contact, String contact2, String location) {
            this.name = name;
            this.description = description;
            this.contact = contact;
            this.contact2 = contact2;
            this.location = location;
        }
    }
}