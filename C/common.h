#ifndef COMMON_H
#define COMMON_H

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define INF 9999
#define MAX_SERVERS 5

// Structure de base d'une tâche
typedef struct Task {
    int id;
    char description[50];
    int priority; 
} Task;

// Créateur de tâche utilitaire
static inline Task createTask(int id, char *desc, int prio) {
    Task t;
    t.id = id;
    strncpy(t.description, desc, 49);
    t.description[49] = '\0';
    t.priority = prio;
    return t;
}

#endif