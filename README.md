# Jenkins Spark.io Plugin

> A plugin that triggers your [Spark.io](http://spark.io) device when Jenkins build fails.

This plugin adds a post-build action which allows to execute a function on your Spark device. 
The call is invoked via a REST call to [Spark Cloud API](http://docs.spark.io/api/) so all you need is a Spark core connected to the cloud and a head full of IoT ideas for your build failed notificator ;) 

## Configuration

Go to *Manage Jenkins* -> *Configure system* and setup following Spark notifier propoerties:
* **Spark access token** - your Spark API access token
* **Device id** - your Spark core id
* **Function** - name of a function called by the plugin via Spark API (see sample code below)

## Sample Spark.io code

```c
int pin = D0;

int trigger(String command);

void setup() {
    pinMode(pin, OUTPUT);
    Spark.function("trigger", trigger); // this must match the "Function" property in plugin configuration
}

void loop() {
}

int trigger(String command) {
    digitalWrite(pin, HIGH);
    delay(400);
    digitalWrite(pin, LOW);
    return 1;
}
```

We use async HTTP POST call to execute a function on Spark, so making a 400ms delay won't result in a lag on your Jenkins installation.

## See it in action!

This video shows a Spark Core connected to Arduino with Adafruit's [Wave Shield](http://www.adafruit.com/products/94), which plays a "siren" sound when a build fails. Enjoy!

[![ScreenShot](http://img.youtube.com/vi/qTfU8mA94W8/0.jpg)](https://www.youtube.com/watch?v=qTfU8mA94W8)

