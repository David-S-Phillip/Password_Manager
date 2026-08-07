MVN = mvn

.PHONY: help compile test clean

help:
	@echo "make compile - Build java code using Maven"
	@echo "make test - Run unit tests"
	@echo "make clean - Remove target folder"

compile:
	$(MVN) clean compile

test:
	$(MVN) test

clean:
	$(MVN) clean

#Target that accepts a commit message parameter
# Usage: make push m="your commit message here"
push:
	git add .
	git commit -m "$(m)"
	git push