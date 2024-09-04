
# Diretórios
SRC_DIR = src
BIN_DIR = out

# Classe principal (ajuste para o seu pacote e nome da classe)
MAIN_CLASS = Main

# Encontrar todos os arquivos .java
JAVA_FILES = $(shell find $(SRC_DIR) -name "*.java")

# Comandos
all: $(BIN_DIR)

$(BIN_DIR):
	mkdir -p $(BIN_DIR)

compile: all
	javac -d $(BIN_DIR) $(JAVA_FILES)

run: compile
	java -cp $(BIN_DIR) $(MAIN_CLASS)

clean:
	rm -rf $(BIN_DIR)/*.class

