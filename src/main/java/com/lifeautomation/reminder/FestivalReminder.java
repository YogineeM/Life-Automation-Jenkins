package com.lifeautomation.reminder;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class FestivalReminder {

    public static void main(String[] args) {

        Map<String, String> festivals = new HashMap<>();

        // Format: MM-DD
        festivals.put("01-26", "Republic Day 🇮🇳");
        festivals.put("08-15", "Independence Day 🇮🇳");
        festivals.put("10-02", "Gandhi Jayanti");
        festivals.put("12-25", "Christmas 🎄");

        LocalDate today = LocalDate.now();
        String todayKey = String.format("%02d-%02d",
                today.getMonthValue(),
                today.getDayOfMonth());

        if (festivals.containsKey(todayKey)) {
            System.out.println("🎉 Today is " + festivals.get(todayKey));
            System.out.println("🔔 Reminder: Wish your friends and family!");
        } else {
            System.out.println("📅 No festival today. Have a productive day!");
        }
    }
}
