.PHONY: build run

build:
	gradlew build

run:
	gradlew :runClient

clean:
	gradlew clean
