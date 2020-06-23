1 Скачать wiremock и в папку ./mappings подложить stub файлик: 200.json
2 java -jar wiremock-standalone-2.26.3.jar
3 java -Dname=hystrix-profiler -jar ./hystrix-profiler.jar
4 echo GET http://localhost:8081/spring-cloud/feign | ./vegeta.exe attack -duration=100s -rate=1 -output=results.bin
5 ./vegeta.exe report results.bin
6 ./vegeta.exe plot -title=Results results.bin > results-plot.html