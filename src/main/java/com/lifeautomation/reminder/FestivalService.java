package com.lifeautomation.reminder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;

public class FestivalService {

    public static void main(String[] args) {
        // Load CSV files from resources folder
        InputStream festivalStream = FestivalService.class.getClassLoader().getResourceAsStream("festivals.csv");
        InputStream checklistStream = FestivalService.class.getClassLoader().getResourceAsStream("checklist.csv");

        if (festivalStream == null) {
            System.out.println("⚠️ festivals.csv not found in resources!");
            return;
        }

        if (checklistStream == null) {
            System.out.println("⚠️ checklist.csv not found in resources!");
            return;
        }

        LocalDate today = LocalDate.now();
        String todayKey = String.format("%02d-%02d", today.getMonthValue(), today.getDayOfMonth());

        boolean festivalFound = false;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(festivalStream))) {
            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(todayKey)) {
                    festivalFound = true;
                    System.out.println("🎉 TODAY IS: " + data[1]);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!festivalFound) {
            System.out.println("📅 No festival today. Have a productive day!");
        }

        // Optional: Read checklist.csv similarly if needed
        // You can add code here to process checklist items
    }
}
