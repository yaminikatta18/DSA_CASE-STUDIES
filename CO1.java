class PetNode {
    int petID;
    String petName;
    int height;
    PetNode left, right;

    PetNode(int petID, String petName) {
        this.petID = petID;
        this.petName = petName;
        this.height = 1;
        left = right = null;
    }
}

public class CO1 {

    PetNode root;

    int height(PetNode node) {
        if (node == null)
            return 0;
        return node.height;
    }

    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    int getBalance(PetNode node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    PetNode rightRotate(PetNode y) {
        PetNode x = y.left;
        PetNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        return x;
    }

    PetNode leftRotate(PetNode x) {
        PetNode y = x.right;
        PetNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        return y;
    }

    PetNode insert(PetNode node, int petID, String petName) {

        if (node == null)
            return new PetNode(petID, petName);

        if (petID < node.petID)
            node.left = insert(node.left, petID, petName);
        else if (petID > node.petID)
            node.right = insert(node.right, petID, petName);
        else
            return node;

        node.height = 1 + max(height(node.left), height(node.right));

        int balance = getBalance(node);

        // LL Rotation
        if (balance > 1 && petID < node.left.petID)
            return rightRotate(node);

        // RR Rotation
        if (balance < -1 && petID > node.right.petID)
            return leftRotate(node);

        // LR Rotation
        if (balance > 1 && petID > node.left.petID) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL Rotation
        if (balance < -1 && petID < node.right.petID) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    PetNode minValueNode(PetNode node) {
        PetNode current = node;

        while (current.left != null)
            current = current.left;

        return current;
    }

    PetNode deleteNode(PetNode root, int petID) {

        if (root == null)
            return root;

        if (petID < root.petID)
            root.left = deleteNode(root.left, petID);

        else if (petID > root.petID)
            root.right = deleteNode(root.right, petID);

        else {

            if ((root.left == null) || (root.right == null)) {

                PetNode temp;

                if (root.left != null)
                    temp = root.left;
                else
                    temp = root.right;

                if (temp == null) {
                    root = null;
                } else {
                    root = temp;
                }

            } else {

                PetNode temp = minValueNode(root.right);

                root.petID = temp.petID;
                root.petName = temp.petName;

                root.right = deleteNode(root.right, temp.petID);
            }
        }

        if (root == null)
            return root;

        root.height = 1 + max(height(root.left), height(root.right));

        int balance = getBalance(root);

        // LL
        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        // LR
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // RR
        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        // RL
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    PetNode search(PetNode root, int petID) {

        if (root == null || root.petID == petID)
            return root;

        if (petID < root.petID)
            return search(root.left, petID);

        return search(root.right, petID);
    }

    void inorder(PetNode root) {
        if (root != null) {
            inorder(root.left);
            System.out.println("Pet ID: " + root.petID +
                    " | Pet Name: " + root.petName);
            inorder(root.right);
        }
    }

    public static void main(String[] args) {

        CO1 tree = new CO1();

        tree.root = tree.insert(tree.root, 101, "Tommy");
        tree.root = tree.insert(tree.root, 105, "Bella");
        tree.root = tree.insert(tree.root, 103, "Rocky");
        tree.root = tree.insert(tree.root, 102, "Lucy");
        tree.root = tree.insert(tree.root, 104, "Max");

        System.out.println("Pet Records:");
        tree.inorder(tree.root);

        int searchID = 103;
        PetNode found = tree.search(tree.root, searchID);

        if (found != null)
            System.out.println("\nPet Found: " + found.petName);
        else
            System.out.println("\nPet Not Found");

        System.out.println("\nDeleting Pet ID 102...");
        tree.root = tree.deleteNode(tree.root, 102);

        System.out.println("\nAfter Deletion:");
        tree.inorder(tree.root);
    }
}