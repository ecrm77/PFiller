## PFiller

Java GUI Application - Website Password Filler Using Chromedriver. Written and tested on MAC. For Windows and Linux, you need some adjustments.

This Application can log in User automatically to a specific webpage with one-page login or two-page login structure.
The User needs to add the XPaths (for Username Field, Password Field, and Submit Button) from the website Form into the Application. All the information will be secured and encrypted in the Application Database.
The User can click on the website link that he/she created for the Application, and the Chrome Application will launch and fill in the information for the login.

## Dependencies and folder structure.

- Load the Maven dependencies into the project. 
- Download and extract the Derby driver and add the "lib" folder to the "PFiller" folder.
  Download Derby: https://db.apache.org/derby/derby_downloads.html
- Download the Chromedriver the same version of installed Chrome.
  Download Chromedriver: https://chromedriver.chromium.org/downloads
- Add the Chromedriver to the "res" folder.


## Run the Application in IDE and setup.

- Click on "Run" to start the Application
- Choose a username and password 
- Log into the Application
- The Application creates a folder under user.home/PFiller.
- Setup example:
- Go to the Settings in Application and click on "Add a new website to Database (one-page login).
- Open the website https://github.com/login
- Click on the username field, click right and Inspect. It opens on the right of the HTML Elements and click one more time right on the highlighted element and Copy->Copy XPath. Paste the value into the Username field in the Application. Do the same for the password field and Sing in button ( right-click on the button without clicking)
 
- <img width="687" alt="Screen Shot 2021-11-05 at 07 03 00" src="https://user-images.githubusercontent.com/93434712/140465695-3e37ade9-9e9e-49bc-9dd1-c2bf67503188.png">

- Click on the top button Reload Database in the Application and click Main.

- <img width="393" alt="Screen Shot 2021-11-05 at 07 05 16" src="https://user-images.githubusercontent.com/93434712/140467711-3d29683c-80b7-4aba-b3a5-5bb99fb30259.png">

- If you click on GitHub, Chrome will launch and open up the GitHub login page, fill in the user information, and log in. One more click on the same closes the Chrome.


## Create installer package for MAC (.pkg)

- Build an artifact for the project on your IDEA, and that creates a JAR File.
- The Information about java packaging guide: https://docs.oracle.com/en/java/javase/14/jpackage/packaging-overview.html#GUID-C1027043-587D-418D-8188-EF8F44A4C06A 
- Here is an example for MAC:
>/Users/Home/Library/Java/JavaVirtualMachines/openjdk-14.0.1/Contents/Home/bin/jpackage --type pkg --description "Browser password filler" --vendor "ecrm77" --app-version 1.0 --input input --dest output --name PFiller --icon /Users/Home/Desktop/build/res/pfiller128.icns --main-jar PFiller.jar




  
  
