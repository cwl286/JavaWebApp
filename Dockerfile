FROM openjdk

WORKDIR /target

#Create directory and copy code
ADD target /target

#Exec form of ENTRYPOINT allows you to set commands and parameters
ENTRYPOINT ["/bin/bash"]

#You can also set CMD default command which will be executed when running container without specifying a command. 
#CMD java -jar /target/demo-1.jar