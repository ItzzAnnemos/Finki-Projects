#include<iostream>
#include<cstring>

using namespace std;

enum Extension {
    txt, pdf, exe
};

class File {
private:
    char *nameDat;
    Extension type;
    char *name;
    int size;
public:
    File() {
        nameDat = nullptr;
        name = nullptr;
        size = 0;
    }

    File(char *nameDat, char *name, int size, Extension type) {
        this->nameDat = new char[strlen(nameDat) + 1];
        strcpy(this->nameDat, nameDat);
        this->type = type;
        this->name = new char[strlen(name) + 1];
        strcpy(this->name, name);
        this->size = size;
    }

    File(const File &x) {
        this->nameDat = new char[strlen(x.nameDat) + 1];
        strcpy(this->nameDat, x.nameDat);
        this->type = x.type;
        this->name = new char[strlen(x.name) + 1];
        strcpy(this->name, x.name);
        this->size = x.size;
    }

    File operator=(const File &x) {
        if (this != &x) {
            delete[] nameDat;
            delete[] name;
            this->nameDat = new char[strlen(x.nameDat) + 1];
            strcpy(this->nameDat, x.nameDat);
            this->type = x.type;
            this->name = new char[strlen(x.name) + 1];
            strcpy(this->name, x.name);
            this->size = x.size;
        }
        return *this;
    }

    ~File() {
        delete[] nameDat;
        delete[] name;
    };

    void print() {
        cout << "File name: " << nameDat << ".";
        if (type == 0) {
            cout << "pdf" << endl;
        } else if (type == 1) {
            cout << "txt" << endl;
        } else {
            cout << "exe" << endl;
        }
        cout << "File owner: " << name << endl;
        cout << "File size: " << size << endl;
    }

    bool equals(const File &that) {
        if ((strcmp(this->nameDat, that.nameDat) == 0) && this->type == that.type &&
            (strcmp(this->name, that.name) == 0)) {
            return true;
        } else
            return false;
    }

    bool equalsType(const File &that) {
        if ((strcmp(this->nameDat, that.nameDat) == 0) && this->type == that.type) {
            return true;
        } else
            return false;
    }
};

class Folder {
private:
    char *name;
    int n = 0;
    File *files;
public:
    Folder(const char *name) {
        this->name = new char[strlen(name) + 1];
        strcpy(this->name, name);
        this->n = 0;
        this->files = nullptr;
    }

    ~Folder() {
        delete[] name;
        delete[] files;
    }

    void print() {
        cout << "Folder name: " << name << endl;
        for (int i = 0; i < n; i++) {
            files[i].print();
        }
    }

    void remove(const File &file) {
        int newI, i, j = 0;
        for (i = 0; i < n; i++) {
            if (files[i].equals(file)) {
                newI = i;
            }
        }
        File temp[n];
        for (i = 0; i < n; i++) {
            temp[i] = files[i];
        }
        delete[] files;
        files = new File[n - 1];
        for (i = 0; i < n; i++) {
            if (i != newI) {
                files[j] = temp[i];
                j++;
            } else
                continue;
        }
        n--;
    }

    void add(const File &file) {
        int i;
        File *temp = new File[n];
        for (i = 0; i < n; i++) {
            temp[i] = files[i];
        }
        delete[] files;
        files = new File[n + 1];
        for (i = 0; i < n; i++) {
            files[i] = temp[i];
        }
        files[n] = file;
        n++;
    }
};

int main() {
    char fileName[20];
    char fileOwner[20];
    int ext;
    int fileSize;

    int testCase;
    cin >> testCase;
    if (testCase == 1) {
        cout << "======= FILE CONSTRUCTORS AND = OPERATOR =======" << endl;
        cin >> fileName;
        cin >> fileOwner;
        cin >> fileSize;
        cin >> ext;

        File created = File(fileName, fileOwner, fileSize, (Extension) ext);
        File copied = File(created);
        File assigned = created;

        cout << "======= CREATED =======" << endl;
        created.print();
        cout << endl;
        cout << "======= COPIED =======" << endl;
        copied.print();
        cout << endl;
        cout << "======= ASSIGNED =======" << endl;
        assigned.print();
    } else if (testCase == 2) {
        cout << "======= FILE EQUALS & EQUALS TYPE =======" << endl;
        cin >> fileName;
        cin >> fileOwner;
        cin >> fileSize;
        cin >> ext;

        File first(fileName, fileOwner, fileSize, (Extension) ext);
        first.print();

        cin >> fileName;
        cin >> fileOwner;
        cin >> fileSize;
        cin >> ext;

        File second(fileName, fileOwner, fileSize, (Extension) ext);
        second.print();

        cin >> fileName;
        cin >> fileOwner;
        cin >> fileSize;
        cin >> ext;

        File third(fileName, fileOwner, fileSize, (Extension) ext);
        third.print();

        bool equals = first.equals(second);
        cout << "FIRST EQUALS SECOND: ";
        if (equals)
            cout << "TRUE" << endl;
        else
            cout << "FALSE" << endl;

        equals = first.equals(third);
        cout << "FIRST EQUALS THIRD: ";
        if (equals)
            cout << "TRUE" << endl;
        else
            cout << "FALSE" << endl;

        bool equalsType = first.equalsType(second);
        cout << "FIRST EQUALS TYPE SECOND: ";
        if (equalsType)
            cout << "TRUE" << endl;
        else
            cout << "FALSE" << endl;

        equalsType = second.equals(third);
        cout << "SECOND EQUALS TYPE THIRD: ";
        if (equalsType)
            cout << "TRUE" << endl;
        else
            cout << "FALSE" << endl;

    } else if (testCase == 3) {
        cout << "======= FOLDER CONSTRUCTOR =======" << endl;
        cin >> fileName;
        Folder folder(fileName);
        folder.print();

    } else if (testCase == 4) {
        cout << "======= ADD FILE IN FOLDER =======" << endl;
        char name[20];
        cin >> name;
        Folder folder(name);

        int iter;
        cin >> iter;

        while (iter > 0) {
            cin >> fileName;
            cin >> fileOwner;
            cin >> fileSize;
            cin >> ext;

            File file(fileName, fileOwner, fileSize, (Extension) ext);
            folder.add(file);
            iter--;
        }
        folder.print();
    } else {
        cout << "======= REMOVE FILE FROM FOLDER =======" << endl;
        char name[20];
        cin >> name;
        Folder folder(name);

        int iter;
        cin >> iter;

        while (iter > 0) {
            cin >> fileName;
            cin >> fileOwner;
            cin >> fileSize;
            cin >> ext;

            File file(fileName, fileOwner, fileSize, (Extension) ext);
            folder.add(file);
            iter--;
        }
        cin >> fileName;
        cin >> fileOwner;
        cin >> fileSize;
        cin >> ext;

        File file(fileName, fileOwner, fileSize, (Extension) ext);
        folder.remove(file);
        folder.print();
    }
    return 0;
}