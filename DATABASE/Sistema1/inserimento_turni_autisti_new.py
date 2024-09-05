import random
from datetime import datetime, timedelta

# Dati forniti
targhe = [ "ABC123XYZA","DEF456WXYZ","GHI789UVWX","JKL012LMNO","PQR345STUV","WXY678MNOP","QWE901BCDE","ZXC234FGHI",
           "VBN567JKLM","RTY890QRST","ASD123NBVC","FGH456TYUI","HJK789UIOP","UIO012MKLP","BVC345UIOA",
           "QWE678FGRT","RTY901CVBN","UIO234GFDS","CVB567LKJH","FGH890PLOI","JKL123MNBV",
           "BNM456UIOP","VBN789XCVB","QWE012CVBN"	
        ]	

numeri_linea = range(1, 16) # TODO: modificare qui con il range giusto

ids_utente = range(2, 11) # TODO:modificare qui con il range giusto

# Funzione per generare date casuali
def generate_random_dates(start_date, end_date, n):
    start_datetime = datetime.strptime(start_date, '%Y-%m-%d')
    end_datetime = datetime.strptime(end_date, '%Y-%m-%d')
    delta = end_datetime - start_datetime
    random_dates = [start_datetime + timedelta(days=random.randint(0, delta.days)) for _ in range(n)]
    return random_dates

# Funzione per generare orari casuali
def generate_random_times_inizio(n):
    random_times = [f"{random.randint(5, 13):02}:{random.randint(0, 59):02}:{random.randint(0, 59):02}" for _ in range(n)]
    return random_times

def generate_random_times_fine(n):
    random_times = [f"{random.randint(8, 22):02}:{random.randint(0, 59):02}:{random.randint(0, 59):02}" for _ in range(n)]
    return random_times

# Genera liste di valori casuali
num_dates = 120  # Numero di date casuali da generare
num_times = 100  # Numero di tempi casuali da generare

random_dates = generate_random_dates('2024-01-01', '2024-06-15', num_dates) # TODO: modificare da che data partire a che data arrivare
random_times_inizio = generate_random_times_inizio(num_times)
random_times_fine = generate_random_times_fine(num_times)

# Lista di intervalli in ore
intervalli = [2, 2.5, 3, 3.5, 4, 4.5, 5, 5.5, 6, 6.5, 7, 7.5, 8]

lista_turni = []

# Registro dei turni per data e autista
registro_turni = {}

# Funzione per verificare sovrapposizione di turni
def sovrapposizione(turni, nuovo_inizio, nuovo_fine):
    for inizio, fine in turni:
        if inizio <= nuovo_inizio < fine or inizio < nuovo_fine <= fine or (nuovo_inizio <= inizio and nuovo_fine >= fine):
            return True
    return False

# Genera le tuple senza sovrapposizioni
for _ in range(350): # TODO: modificare qui per scegliere quanti turni vuoi
    while True:
        data = random.choice(random_dates).strftime('%Y-%m-%d')
        orario_inizio_str = random.choice(random_times_inizio)
        orario_inizio = datetime.strptime(orario_inizio_str, '%H:%M:%S')
        intervallo_ore = random.choice(intervalli)
        orario_fine = orario_inizio + timedelta(hours=intervallo_ore)
        orario_fine_str = orario_fine.strftime('%H:%M:%S')
        targa = random.choice(targhe)
        num_linea = random.choice(numeri_linea)
        id_utente = random.choice(ids_utente)

        chiave = (data, id_utente)
        
        # Verifica se il turno si sovrappone con i turni esistenti
        if chiave not in registro_turni:
            registro_turni[chiave] = []
        
        if not sovrapposizione(registro_turni[chiave], orario_inizio, orario_fine):
            registro_turni[chiave].append((orario_inizio, orario_fine))
            lista_turni.append((data, orario_inizio_str, orario_fine_str, targa, num_linea, id_utente))
            break

# Ordina le tuple in base alla data
lista_turni = sorted(lista_turni, key=lambda x: datetime.strptime(x[0], '%Y-%m-%d'))

# Stampa i turni
for turno in lista_turni:
    print(turno)
    


# Utilizza filter per ottenere tutte le tuple con l'id_utente = 2
turni_utente_2 = list(filter(lambda turno: turno[-1] == 2, lista_turni))
print()
print("LISTA DEI TURNI DELL'UTENTE = 2")
for t_u2 in turni_utente_2:
    print(t_u2)
    
    
    