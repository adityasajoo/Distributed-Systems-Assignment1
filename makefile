# Variables
JAVAC = javac
JAVA = java
OUT_DIR = out
SOURCE_FILES = *.java
PACKAGE = Assignment1
SERVER = CalculatorServer
CLIENT = CalculatorClient

all: compile

$(OUT_DIR):
	@mkdir -p $(OUT_DIR)

compile: $(OUT_DIR)
	@echo "Compiling Java files..."
	$(JAVAC) -d $(OUT_DIR) $(SOURCE_FILES)

# Run the server
run-server: compile
	@echo "Running Calculator Server..."
	$(JAVA) -classpath $(OUT_DIR) $(PACKAGE).$(SERVER)

# Run the client
run-client: compile
	@echo "Running Calculator Client..."
	$(JAVA) -classpath $(OUT_DIR) $(PACKAGE).$(CLIENT)

# Clean up compiled files
clean:
	@echo "Cleaning up..."
	@rm -rf $(OUT_DIR)

run-all: run-server run-client

.PHONY: all compile start-registry run-server run-client clean run-all
