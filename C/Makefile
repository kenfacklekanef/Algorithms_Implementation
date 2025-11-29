CC = gcc
CFLAGS = -Wall -Wextra -g

# Liste des fichiers objets
OBJ = main.o bst.o sort.o graph.o

# Nom de l'exécutable final
EXEC = projet_algo

all: $(EXEC)

$(EXEC): $(OBJ)
	$(CC) $(CFLAGS) -o $@ $^

%.o: %.c
	$(CC) $(CFLAGS) -c $<

clean:
	rm -f *.o $(EXEC)