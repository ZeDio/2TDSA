/*
LCD I²C HELLO WORLD

MADE BY BRAYLEN HAMMOND

YOU MAY USE THE CODE AND DIAGRAM TO HELP YOU WITH YOUR LCD.
THIS CODE DOESNT APPLY TO THE NORMAL LCD, IT ONLY
APPLIES TO THE I2C LCD. YOU CAN DO THIS ON WOKWI OR
ON YOUR ACTUAL ARDUINO. THIS PROJECT IS ONLY 
TESTED THE ARDUINO UNO, YOU CAN TRY ON OTHER
ARDUINOES TO SEE IF IT WORKS. 

GO TO MY YOUTUBE CHANNEL FOR MORE HELP WITH CODING AN ARDUINO. Please Subscribe, it's free and it'll help out a ton.
https://www.youtube.com/channel/UCccMHKRai1dwgxwFg-w8hZA


Have fun, be creative and enjoy the help I do for you
*/

// Add the Liquid Crystal I2C library
#include <LiquidCrystal_I2C.h>

// Add the lcd
LiquidCrystal_I2C lcd(0x27, 16, 2);

void setup() {
   // Initalise the LCD
   lcd.init();
   // Turn on the LCD backlight
   lcd.backlight();
   // Put text on the LCD
   lcd.print("Hello World!");
}

void loop() {
  // No code needed for this part, you can put your code here if you want.
}