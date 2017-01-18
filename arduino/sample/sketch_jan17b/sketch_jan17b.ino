const int buttonPin = 4;     // the number of the pushbutton pin
const int ledPin =  13;      // the number of the LED pin
long baudRate = 115200;

// variables will change:
int buttonState = 0;         // variable for reading the pushbutton status


void setup() {
     Serial.begin(baudRate);
  
  // initialize the LED pin as an output:
  pinMode(ledPin, OUTPUT);
  // initialize the pushbutton pin as an input:
  pinMode(buttonPin, INPUT);
}

void loop() {
  // read the state of the pushbutton value:
  buttonState = digitalRead(buttonPin);
  
  // if it is, the buttonState is HIGH:
  if (buttonState == HIGH) {
        Serial.write("I AM ON\n");
        Serial.flush();
        delay(1000);
  } 
}
