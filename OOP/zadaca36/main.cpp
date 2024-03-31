#include <iostream>
#include <cstdlib>
#include <cstring>

#define UNKNOWN = "unknown";

using namespace std;

class Image {
protected:
    char *ime;
    char korisnik[50];
    int a;
    int b;
public:
    Image(char *ime = "untitled", char *korisnik = "unknown", int a = 800, int b = 800) {
        this->ime = new char[strlen(ime) + 1];
        strcpy(this->ime, ime);
        strcpy(this->korisnik, korisnik);
        this->a = a;
        this->b = b;
    }

    Image(const Image &x) {
        this->ime = new char[strlen(x.ime) + 1];
        strcpy(this->ime, x.ime);
        strcpy(this->korisnik, x.korisnik);
        this->a = x.a;
        this->b = x.b;
    }

    Image operator=(const Image &x) {
        if (this != &x) {
            delete[] ime;
            this->ime = new char[strlen(x.ime) + 1];
            strcpy(this->ime, x.ime);
            strcpy(this->korisnik, x.korisnik);
            this->a = x.a;
            this->b = x.b;
        }
        return *this;
    }

    ~Image() {
        delete[] ime;
    }

    virtual int fileSize() {
        return a * b * 3;
    }

    friend ostream &operator<<(ostream &out, Image &x) {
        out << x.ime << " " << x.korisnik << " " << x.fileSize() << endl;
        return out;
    }

    bool operator>(Image &x) {
        if (this->fileSize() > x.fileSize())
            return true;
        else
            return false;
    }

    void print() {
        cout << ime << " " << korisnik << " " << fileSize() << endl;
    }
};

class TransparentImage : public Image {
protected:
    bool daliTrans;
public:
    TransparentImage(char *ime = "untitled", char *korisnik = "unknown", int a = 800, int b = 800,
                     bool daliTrans = true) : Image(ime, korisnik, a, b) {
        this->daliTrans = daliTrans;
    }

    TransparentImage(const TransparentImage &x) : Image(x) {
        this->daliTrans = x.daliTrans;
    }

    TransparentImage &operator=(const TransparentImage &x) {
        if (this != &x) {
            delete ime;
            this->ime = new char[strlen(x.ime) + 1];
            strcpy(this->ime, x.ime);
            strcpy(this->korisnik, x.korisnik);
            this->a = x.a;
            this->b = x.b;
            this->daliTrans = x.daliTrans;
        }
        return *this;
    }

    ~TransparentImage() {
    }

    int fileSize() {
        if (daliTrans) {
            return a * b * 4;
        } else {
            return a * b + (a * b) / 8;
        }
    }

    friend ostream &operator<<(ostream &out, TransparentImage &x) {
        out << x.ime << " " << x.korisnik << " " << x.fileSize() << endl;
        return out;
    }

    bool operator>(TransparentImage &x) {
        if (this->fileSize() > x.fileSize())
            return true;
        else
            return false;
    }

    void print() {
        cout << ime << " " << korisnik << " " << fileSize() << endl;
    }
};

class Folder {
protected:
    char ime[255];
    char korisnik[50];
    Image *niza[100];
    int n;
public:
    Folder(char *ime = "", char *korisnik = "unknown") {
        strcpy(this->ime, ime);
        strcpy(this->korisnik, korisnik);
        this->n = 0;
    }

    Folder(const Folder &x) {
        strcpy(this->ime, x.ime);
        strcpy(this->korisnik, x.korisnik);
        for (int i = 0; i < x.n; i++) {
            this->niza[i] = x.niza[i];
        }
        this->n = x.n;
    }

    int folderSize() {
        int vkupno = 0;
        for (int i = 0; i < n; i++) {
            vkupno += niza[i]->fileSize();
        }
        return vkupno;
    }

    Image *getMaxFile() {
        int index, max = 0;
        for (int i = 0; i < n; i++) {
            if (niza[i]->fileSize() > max) {
                max = niza[i]->fileSize();
                index = i;
            }
        }
        return niza[index];
    }

    Folder &operator+=(Image &x) {
        this->niza[n++] = &x;
        return *this;
    }

    Folder &operator+=(TransparentImage &x) {
        this->niza[n++] = &x;
        return *this;
    }

    friend ostream &operator<<(ostream &out, Folder &x) {
        out << x.ime << " " << x.korisnik << endl;
        out << "--" << endl;
        for (int i = 0; i < x.n; i++) {
            x.niza[i]->print();
        }
        out << "--" << endl;
        out << "Folder size: " << x.folderSize() << endl;
        return out;
    }

    Image *operator[](int index) {
        if (index < 0 || index > n) return 0;
        Image *max = niza[index];
        return max;
    }
};

Folder &max_folder_size(Folder *niza, int n) {
    int index, max = 0;
    for (int i = 0; i < n; i++) {
        if (niza[i].folderSize() > max) {
            max = niza[i].folderSize();
            index = i;
        }
    }
    return niza[index];
}

int main() {

    int tc; // Test Case

    char name[255];
    char user_name[51];
    int w, h;
    bool tl;

    cin >> tc;

    if (tc == 1) {
        // Testing constructor(s) & operator << for class File

        cin >> name;
        cin >> user_name;
        cin >> w;
        cin >> h;


        Image f1;

        cout << f1;

        Image f2(name);
        cout << f2;

        Image f3(name, user_name);
        cout << f3;

        Image f4(name, user_name, w, h);
        cout << f4;
    } else if (tc == 2) {
        // Testing constructor(s) & operator << for class TextFile
        cin >> name;
        cin >> user_name;
        cin >> w >> h;
        cin >> tl;

        TransparentImage tf1;
        cout << tf1;

        TransparentImage tf4(name, user_name, w, h, tl);
        cout << tf4;
    } else if (tc == 3) {
        // Testing constructor(s) & operator << for class Folder
        cin >> name;
        cin >> user_name;

        Folder f3(name, user_name);
        cout << f3;
    } else if (tc == 4) {
        // Adding files to folder
        cin >> name;
        cin >> user_name;

        Folder dir(name, user_name);

        Image *f;
        TransparentImage *tf;

        int sub, fileType;

        while (1) {
            cin >> sub; // Should we add subfiles to this folder
            if (!sub) break;

            cin >> fileType;
            if (fileType == 1) { // Reading File
                cin >> name;
                cin >> user_name;
                cin >> w >> h;
                f = new Image(name, user_name, w, h);
                dir += *f;
            } else if (fileType == 2) {
                cin >> name;
                cin >> user_name;
                cin >> w >> h;
                cin >> tl;
                tf = new TransparentImage(name, user_name, w, h, tl);
                dir += *tf;
            }
        }
        cout << dir;
    } else if (tc == 5) {
        // Testing getMaxFile for folder

        cin >> name;
        cin >> user_name;

        Folder dir(name, user_name);

        Image *f;
        TransparentImage *tf;

        int sub, fileType;

        while (1) {
            cin >> sub; // Should we add subfiles to this folder
            if (!sub) break;

            cin >> fileType;
            if (fileType == 1) { // Reading File
                cin >> name;
                cin >> user_name;
                cin >> w >> h;
                f = new Image(name, user_name, w, h);
                dir += *f;
            } else if (fileType == 2) {
                cin >> name;
                cin >> user_name;
                cin >> w >> h;
                cin >> tl;
                tf = new TransparentImage(name, user_name, w, h, tl);
                dir += *tf;
            }
        }
        cout << *(dir.getMaxFile());
    } else if (tc == 6) {
        // Testing operator [] for folder

        cin >> name;
        cin >> user_name;

        Folder dir(name, user_name);

        Image *f;
        TransparentImage *tf;

        int sub, fileType;

        while (1) {
            cin >> sub; // Should we add subfiles to this folder
            if (!sub) break;

            cin >> fileType;
            if (fileType == 1) { // Reading File
                cin >> name;
                cin >> user_name;
                cin >> w >> h;
                f = new Image(name, user_name, w, h);
                dir += *f;
            } else if (fileType == 2) {
                cin >> name;
                cin >> user_name;
                cin >> w >> h;
                cin >> tl;
                tf = new TransparentImage(name, user_name, w, h, tl);
                dir += *tf;
            }
        }

        cin >> sub; // Reading index of specific file
        cout << *dir[sub];
    } else if (tc == 7) {
        // Testing function max_folder_size
        int folders_num;

        Folder dir_list[10];

        Folder dir;
        cin >> folders_num;

        for (int i = 0; i < folders_num; ++i) {
            cin >> name;
            cin >> user_name;
            dir = Folder(name, user_name);


            Image *f;
            TransparentImage *tf;

            int sub, fileType;

            while (1) {
                cin >> sub; // Should we add subfiles to this folder
                if (!sub) break;

                cin >> fileType;
                if (fileType == 1) { // Reading File
                    cin >> name;
                    cin >> user_name;
                    cin >> w >> h;
                    f = new Image(name, user_name, w, h);
                    dir += *f;
                } else if (fileType == 2) {
                    cin >> name;
                    cin >> user_name;
                    cin >> w >> h;
                    cin >> tl;
                    tf = new TransparentImage(name, user_name, w, h, tl);
                    dir += *tf;
                }
            }
            dir_list[i] = dir;
        }

        cout << max_folder_size(dir_list, folders_num);
    }
    return 0;
};
