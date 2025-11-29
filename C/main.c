#include "common.h"
#include "bst.h"
#include "sort.h"
#include "graph.h"

int main() {
    BSTNode* root = NULL;
    int choice, id, prio, count = 0;
    char desc[50];
    
    // Données initiales
    root = insertBST(root, createTask(101, "DB Backup", 2));
    root = insertBST(root, createTask(50, "Update OS", 9));
    root = insertBST(root, createTask(70, "Check Logs", 5));
    count = 3;

    printf("=== PROJET MODULAIRE L3: ALGOS AVANCES ===\n");

    while(1) {
        printf("\n1. Ajouter Tache (BST)\n2. Parcours (DFS/BFS)\n3. Tri (QuickSort)\n4. Reseau (Dijkstra/Kruskal)\n5. Quitter\nChoix: ");
        if(scanf("%d", &choice) != 1) break;

        switch(choice) {
            case 1:
                printf("ID: "); scanf("%d", &id);
                printf("Desc: "); scanf("%s", desc);
                printf("Prio: "); scanf("%d", &prio);
                root = insertBST(root, createTask(id, desc, prio));
                count++;
                break;
            case 2:
                printf("\n[DFS InOrder] "); DFS_InOrder(root);
                BFS_LevelOrder(root);
                break;
            case 3:
                printf("\n--- QuickSort par Priorite ---\n");
                Task* arr = malloc(count * sizeof(Task));
                int idx = 0;
                flattenBST(root, &arr, &idx);
                quickSort(arr, 0, count - 1);
                for(int i=0; i<count; i++) printf("Prio %d: %s\n", arr[i].priority, arr[i].description);
                free(arr);
                break;
            case 4:
                demoDijkstra();
                demoKruskal();
                break;
            case 5:
                freeBST(root);
                return 0;
        }
    }
    return 0;
}