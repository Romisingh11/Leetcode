class Solution {
    public double angleClock(int hour, int minutes) {
        // Position of hour hand
        double hourAngle = (hour % 12) * 30 + minutes * 0.5;
        
        // Position of minute hand
        double minuteAngle = minutes * 6;
        
        // Absolute difference
        double diff = Math.abs(hourAngle - minuteAngle);
        
        // Smaller angle
        return Math.min(diff, 360 - diff);
    }
}