To start this project you need docker installed and your docker daemon needs to be running.

then run the command from in the root folder 
> docker-compose up

To test the API you can run the following command from inside the root folder
> curl -X POST "http://localhost:8080/api/file" -H "Content-Type: text/plain" --data "@input.txt"  >> output.txt
