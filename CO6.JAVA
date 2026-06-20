import java.util.*;

// ---------------- PET CLASS ----------------
class Pet {
    int petId;
    String petName;
    String ownerName;
    String petType;
    int age;

    Pet(int petId, String petName, String ownerName, String petType, int age) {
        this.petId = petId;
        this.petName = petName;
        this.ownerName = ownerName;
        this.petType = petType;
        this.age = age;
    }

    void display() {
        System.out.println("Pet ID      : " + petId);
        System.out.println("Pet Name    : " + petName);
        System.out.println("Owner Name  : " + ownerName);
        System.out.println("Pet Type    : " + petType);
        System.out.println("Age         : " + age);
    }
}

// ---------------- BST FOR PET RECORDS ----------------
class BSTNode {
    Pet pet;
    BSTNode left, right;

    BSTNode(Pet pet) {
        this.pet = pet;
        left = right = null;
    }
}

class PetBST {
    BSTNode root;

    BSTNode insert(BSTNode root, Pet pet) {
        if (root == null)
            return new BSTNode(pet);

        if (pet.petId < root.pet.petId)
            root.left = insert(root.left, pet);
        else if (pet.petId > root.pet.petId)
            root.right = insert(root.right, pet);

        return root;
    }

    void insertPet(Pet pet) {
        root = insert(root, pet);
    }

    BSTNode search(BSTNode root, int petId) {
        if (root == null || root.pet.petId == petId)
            return root;

        if (petId < root.pet.petId)
            return search(root.left, petId);
        else
            return search(root.right, petId);
    }

    void searchPet(int petId) {
        BSTNode result = search(root, petId);
        if (result != null) {
            System.out.println("\nPet Found:");
            result.pet.display();
        } else {
            System.out.println("Pet not found.");
        }
    }

    void inorder(BSTNode root) {
        if (root != null) {
            inorder(root.left);
            root.pet.display();
            System.out.println("-------------------------");
            inorder(root.right);
        }
    }

    void displayAllPets() {
        if (root == null) {
            System.out.println("No pet records available.");
            return;
        }
        System.out.println("\n--- PET RECORDS (BST Inorder Traversal) ---");
        inorder(root);
    }
}

// ---------------- AVL TREE FOR TREATMENTS ----------------
class Treatment {
    int treatmentId;
    String treatmentName;
    int cost;

    Treatment(int treatmentId, String treatmentName, int cost) {
        this.treatmentId = treatmentId;
        this.treatmentName = treatmentName;
        this.cost = cost;
    }

    void display() {
        System.out.println("Treatment ID   : " + treatmentId);
        System.out.println("Treatment Name : " + treatmentName);
        System.out.println("Cost           : ₹" + cost);
    }
}

class AVLNode {
    Treatment treatment;
    AVLNode left, right;
    int height;

    AVLNode(Treatment treatment) {
        this.treatment = treatment;
        height = 1;
    }
}

class TreatmentAVL {
    AVLNode root;

    int height(AVLNode node) {
        return node == null ? 0 : node.height;
    }

    int getBalance(AVLNode node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    AVLNode insert(AVLNode node, Treatment treatment) {
        if (node == null)
            return new AVLNode(treatment);

        if (treatment.treatmentId < node.treatment.treatmentId)
            node.left = insert(node.left, treatment);
        else if (treatment.treatmentId > node.treatment.treatmentId)
            node.right = insert(node.right, treatment);
        else
            return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = getBalance(node);

        // LL Rotation
        if (balance > 1 && treatment.treatmentId < node.left.treatment.treatmentId)
            return rightRotate(node);

        // RR Rotation
        if (balance < -1 && treatment.treatmentId > node.right.treatment.treatmentId)
            return leftRotate(node);

        // LR Rotation
        if (balance > 1 && treatment.treatmentId > node.left.treatment.treatmentId) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL Rotation
        if (balance < -1 && treatment.treatmentId < node.right.treatment.treatmentId) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    void insertTreatment(Treatment treatment) {
        root = insert(root, treatment);
    }

    void inorder(AVLNode root) {
        if (root != null) {
            inorder(root.left);
            root.treatment.display();
            System.out.println("-------------------------");
            inorder(root.right);
        }
    }

    void displayTreatments() {
        if (root == null) {
            System.out.println("No treatment records available.");
            return;
        }
        System.out.println("\n--- TREATMENT RECORDS (AVL Inorder Traversal) ---");
        inorder(root);
    }
}

// ---------------- GRAPH FOR DOCTOR-PET RELATIONSHIP ----------------
class Graph {
    private final int vertices;
    private final LinkedList<Integer>[] adjList;

    Graph(int vertices) {
        this.vertices = vertices;
        adjList = new LinkedList[vertices];
        for (int i = 0; i < vertices; i++)
            adjList[i] = new LinkedList<>();
    }

    void addEdge(int u, int v) {
        adjList[u].add(v);
        adjList[v].add(u); // undirected graph
    }

    void bfs(int start) {
        boolean[] visited = new boolean[vertices];
        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.add(start);

        System.out.print("BFS Traversal: ");
        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");

            for (int neighbor : adjList[node]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    void dfsUtil(int node, boolean[] visited) {
        visited[node] = true;
        System.out.print(node + " ");

        for (int neighbor : adjList[node]) {
            if (!visited[neighbor])
                dfsUtil(neighbor, visited);
        }
    }

    void dfs(int start) {
        boolean[] visited = new boolean[vertices];
        System.out.print("DFS Traversal: ");
        dfsUtil(start, visited);
        System.out.println();
    }
}

// ---------------- MAIN CLASS ----------------
public class CO6 {
    static Scanner sc = new Scanner(System.in);
    static PetBST petTree = new PetBST();
    static TreatmentAVL treatmentTree = new TreatmentAVL();
    static Graph graph = new Graph(10); // sample graph with 10 nodes

    static void addPet() {
        System.out.print("Enter Pet ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Pet Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Owner Name: ");
        String owner = sc.nextLine();

        System.out.print("Enter Pet Type: ");
        String type = sc.nextLine();

        System.out.print("Enter Age: ");
        int age = sc.nextInt();

        Pet pet = new Pet(id, name, owner, type, age);
        petTree.insertPet(pet);

        System.out.println("Pet record inserted into BST successfully.\n");
    }

    static void searchPet() {
        System.out.print("Enter Pet ID to search: ");
        int id = sc.nextInt();
        petTree.searchPet(id);
        System.out.println();
    }

    static void addTreatment() {
        System.out.print("Enter Treatment ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Treatment Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Treatment Cost: ");
        int cost = sc.nextInt();

        Treatment treatment = new Treatment(id, name, cost);
        treatmentTree.insertTreatment(treatment);

        System.out.println("Treatment record inserted into AVL Tree successfully.\n");
    }

    static void addGraphConnection() {
        System.out.print("Enter Source Node: ");
        int u = sc.nextInt();

        System.out.print("Enter Destination Node: ");
        int v = sc.nextInt();

        graph.addEdge(u, v);
        System.out.println("Connection added successfully.\n");
    }

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("==========================================");
            System.out.println(" NON LINEAR DATA STRUCTURES SYSTEM ");
            System.out.println("==========================================");
            System.out.println("1. Add Pet Record (BST)");
            System.out.println("2. Search Pet Record (BST)");
            System.out.println("3. Display All Pet Records");
            System.out.println("4. Add Treatment Record (AVL)");
            System.out.println("5. Display Treatment Records");
            System.out.println("6. Add Doctor-Pet Connection (Graph)");
            System.out.println("7. BFS Traversal");
            System.out.println("8. DFS Traversal");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addPet();
                    break;
                case 2:
                    searchPet();
                    break;
                case 3:
                    petTree.displayAllPets();
                    break;
                case 4:
                    addTreatment();
                    break;
                case 5:
                    treatmentTree.displayTreatments();
                    break;
                case 6:
                    addGraphConnection();
                    break;
                case 7:
                    System.out.print("Enter starting node for BFS: ");
                    int bfsStart = sc.nextInt();
                    graph.bfs(bfsStart);
                    System.out.println();
                    break;
                case 8:
                    System.out.print("Enter starting node for DFS: ");
                    int dfsStart = sc.nextInt();
                    graph.dfs(dfsStart);
                    System.out.println();
                    break;
                case 9:
                    System.out.println("Exiting CO6 System...");
                    break;
                default:
                    System.out.println("Invalid choice.\n");
            }

        } while (choice != 9);
    }
}