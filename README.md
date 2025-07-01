# tradesocioAssessment

 -- Generated Springboot Project using Spring Intializr.
 -- Used VS code for project handling
    ./mvwn spring-boot:run

     curl --header "Content-Type: application/json" \
     --data '{"username":"admin","password":"root"}' \
     http://localhost:8080/api
 -- Dockerized the project using Dockerfile
 -- eclipse-temurin:17-jdk used as base image. Test it by running locally 

    docker build -t tradesocio-api .
    docker run -p 8080:8080 tradesocio-api
    Is accessible at :- http://localhost:8080/api

 -- Build jenking on EC2 server 
    Plugins needed :-  Docker Pipeline, Pipeline, Git
    docker credentials needed
    use "Pipeline from SCM"
 
 -- created helm chart 
    command :- helm create tradesocio-api

 -- deployed to Amazon EKS
    1) create Eks cluster  : eksctl create cluster \
                            --name tradesocio-cluster \
                            --version 1.29 \
                            --region us-east-1 \
                            --nodegroup-name tradesocio-nodes \
                            --node-type t3.medium \
                            --nodes 2
    2) confirm connection : kubectl get nodes
    3) deployed helm charts : helm install tradesocio-api ./tradesocio-api
    4) verify : kubectl get all
    5) Test API(withou ingress) : kubectl port-forward svc/tradesocio-api 8080:8080